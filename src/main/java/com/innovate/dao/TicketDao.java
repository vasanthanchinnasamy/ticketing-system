package com.innovate.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.innovate.dto.Response;
import com.innovate.dto.Ticket;


public interface TicketDao{

	public Ticket addTicket(Ticket ticket);
	
	public List<Ticket> getAllTicket();
	
	public List<Ticket> getFilteredTickets(Long assignedToUser,Long customerId,Long statusId);
	
	public Ticket getTicket(Long ticketId);
	
	public Ticket updateTicket(Ticket ticket);
	
	public Ticket updateTicket(Long ticketId,Map<String, Object> inputData);
	
	public int updateStatus(Long ticketId,Long statusId);
	
	public int assignTicket(Long ticketId,Long assignedToUser);
	
	public int deleteTicket(Long ticketId);
	
	public Response addResponse(Long ticketId,String responseText);

	public int closeResolvedTasks(LocalDateTime dateBefore30Days);

}
