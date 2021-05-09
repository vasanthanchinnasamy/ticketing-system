package com.innovate.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.innovate.dto.Priority;
import com.innovate.dto.Response;
import com.innovate.dto.Status;
import com.innovate.dto.Ticket;
import com.innovate.dto.User;

@Repository
@Transactional
public class TicketDaoImpl implements TicketDao{
	
	
	@PersistenceContext
	EntityManager entityManager;
    

	@Override
	public Ticket addTicket(Ticket ticket) {
		if(Objects.isNull(ticket.getAssignedToUserId())) {
			Optional<Object> resultUser = assignTicketBasedOnLoad();
			resultUser.ifPresent(userId->{
				User user = new User();
				user.setUserId(Long.parseLong(userId.toString()));
				ticket.setAssignedToUser(user);
			});
		}else {
			User user = new User();
			user.setUserId(ticket.getAssignedToUserId());
			ticket.setAssignedToUser(user);
		}
		entityManager.persist(ticket);
		return ticket;
	}


	@Override
	public List<Ticket> getAllTicket() {
		return entityManager.createQuery("SELECT t FROM Ticket t",Ticket.class).getResultList();
	}


	@Override
	public List<Ticket> getFilteredTickets(Long assignedToUser, Long customerId, Long statusId) {
		return entityManager.createQuery("SELECT t FROM Ticket t where t.assignedToUser.userId=:assignedToUser "
				+ "or t.customerId=:customerId or t.status.statusId=:statusId",Ticket.class)
				.setParameter("assignedToUser", assignedToUser)
				.setParameter("customerId", customerId)
				.setParameter("statusId", statusId)
				.getResultList();
	}


	@Override
	public Ticket getTicket(Long ticketId) {
		return entityManager.find(Ticket.class, ticketId);
	}


	@Override
	public Ticket updateTicket(Ticket ticket) {
		return entityManager.merge(ticket);
	}


	@Override
	public int updateStatus(Long ticketId,Long statusId) {
		return entityManager.createQuery("UPDATE Ticket t SET t.status.statusId=:statusId,t.statusUpdatedAt=:statusUpdatedAt where t.ticketId=:ticketId  and t.deleteKey=:deleteKey")
		.setParameter("statusId", statusId)
		.setParameter("ticketId", ticketId)
		.setParameter("deleteKey", Boolean.FALSE)
		.setParameter("statusUpdatedAt", LocalDateTime.now())
		.executeUpdate();
	}


	@Override
	public int assignTicket(Long ticketId, Long assignedToUser) {
		return entityManager.createQuery("UPDATE Ticket t SET t.assignedToUser.userId=:assignedToUser where t.ticketId=:ticketId and t.deleteKey=:deleteKey")
				.setParameter("assignedToUser", assignedToUser)
				.setParameter("ticketId", ticketId)
				.setParameter("deleteKey", Boolean.FALSE)
				.executeUpdate();
	}


	@Override
	public int deleteTicket(Long ticketId) {
		return entityManager.createQuery("UPDATE Ticket t SET t.deleteKey=:deleteKey where t.ticketId=:ticketId and  t.deleteKey=:notDeleted")
				.setParameter("ticketId", ticketId)
				.setParameter("deleteKey", Boolean.TRUE)
				.setParameter("notDeleted", Boolean.FALSE)
				.executeUpdate();
	}


	@Override
	public Response addResponse(Long ticketId,String responseText) {
		Response response = new Response(responseText,ticketId,LocalDateTime.now());
		entityManager.persist(response);
		return response;
	}


	@Override
	public Ticket updateTicket(Long ticketId, Map<String, Object> inputData) {
		Ticket ticket = entityManager.find(Ticket.class, ticketId);
		JSONObject jsonObject = new JSONObject(inputData);
		if(!jsonObject.isNull("type")) ticket.setType(jsonObject.getLong("type"));
		if(!jsonObject.isNull("priorityId")) {
			Priority priority = new Priority();
			priority.setPriorityId(jsonObject.getLong("priorityId"));
			ticket.setPriority(priority);
			
		} 
		if(!jsonObject.isNull("statusId")) {
			Status status = new Status();
			status.setStatusId(jsonObject.getLong("statusId"));
			ticket.setStatus(status);
			ticket.setStatusUpdatedAt(LocalDateTime.now());
		} 
		if(!jsonObject.isNull("customerId")) ticket.setCustomerId(jsonObject.getLong("customerId"));
		if(!jsonObject.isNull("title")) ticket.setTitle(jsonObject.getString("title"));
		if(!jsonObject.isNull("description")) ticket.setDescription(jsonObject.getString("description"));
		if(!jsonObject.isNull("createdByUser")) {
			User user = new User();
			user.setUserId(jsonObject.getLong("createdByUser"));
			ticket.setCreatedByUser(user);
			
		} 
		if(!jsonObject.isNull("assignedToUser")) {
			User user = new User();
			user.setUserId(jsonObject.getLong("createdByUser"));
			ticket.setAssignedToUser(user);
		} 
		entityManager.merge(ticket);
		return ticket;
	}


	@Override
	public int closeResolvedTasks(LocalDateTime dateBefore30Days) {
		int updateCount = entityManager.createQuery("UPDATE Ticket t SET t.status.statusId=:closedStatus where t.statusUpdatedAt<=:statusUpdatedAt "
				+ "and t.status.statusId=:resolvedStatus")
				.setParameter("statusUpdatedAt", dateBefore30Days)
				.setParameter("resolvedStatus", 4L)
				.setParameter("closedStatus", 5L)
				.executeUpdate();
		return updateCount;
	}


	@Override
	public Optional<Object> assignTicketBasedOnLoad() {
		Object userId = entityManager.createNativeQuery("select users.user_id assigned_ticket_count from users\r\n"
				+ "left join ticket on ticket.assigned_to_user_id = users.user_id\r\n"
				+ "group by users.user_id order by count(ticket.ticket_id) limit 1;").getSingleResult();
		return Optional.ofNullable(userId);
	}
	
	

}
