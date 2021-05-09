package com.innovate.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.innovate.dto.Response;
import com.innovate.dto.Ticket;

@Repository
@Transactional
public class TicketDaoImpl implements TicketDao{
	
	
	@PersistenceContext
	EntityManager entityManager;
    

	@Override
	public Ticket addTicket(Ticket ticket) {
		entityManager.persist(ticket);
		return ticket;
	}


	@Override
	public List<Ticket> getAllTicket() {
		return entityManager.createQuery("SELECT t FROM Ticket t",Ticket.class).getResultList();
	}


	@Override
	public List<Ticket> getFilteredTickets(String assignedToUser, Long customerId, Long statusId) {
		return entityManager.createQuery("SELECT t FROM Ticket t where t.assignedToUser=:assignedToUser "
				+ "or t.customerId=:customerId or t.statusId=:statusId",Ticket.class)
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
		return entityManager.createQuery("UPDATE Ticket t SET t.statusId=:statusId,t.statusUpdatedAt=:statusUpdatedAt where t.ticketId=:ticketId  and t.deleteKey=:deleteKey")
		.setParameter("statusId", statusId)
		.setParameter("ticketId", ticketId)
		.setParameter("deleteKey", Boolean.FALSE)
		.setParameter("statusUpdatedAt", LocalDateTime.now())
		.executeUpdate();
	}


	@Override
	public int assignTicket(Long ticketId, String assignedToUser) {
		return entityManager.createQuery("UPDATE Ticket t SET t.assignedToUser=:assignedToUser where t.ticketId=:ticketId and t.deleteKey=:deleteKey")
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
		if(!jsonObject.isNull("priorityId")) ticket.setPriorityId(jsonObject.getLong("priorityId"));
		if(!jsonObject.isNull("statusId")) {
			ticket.setStatusId(jsonObject.getLong("statusId"));
			ticket.setStatusUpdatedAt(LocalDateTime.now());
		} 
		if(!jsonObject.isNull("customerId")) ticket.setCustomerId(jsonObject.getLong("customerId"));
		if(!jsonObject.isNull("title")) ticket.setTitle(jsonObject.getString("title"));
		if(!jsonObject.isNull("description")) ticket.setTitle(jsonObject.getString("description"));
		if(!jsonObject.isNull("createdByUser")) ticket.setTitle(jsonObject.getString("createdByUser"));
		if(!jsonObject.isNull("assignedToUser")) ticket.setTitle(jsonObject.getString("assignedToUser"));
		entityManager.merge(ticket);
		return ticket;
	}

}
