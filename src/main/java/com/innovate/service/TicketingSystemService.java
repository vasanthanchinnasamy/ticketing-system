package com.innovate.service;

import java.time.LocalDateTime;
import java.util.Map;

import com.innovate.dto.Ticket;

public interface TicketingSystemService {
	
	
	public Map<String, Object> createTicket(Ticket ticket);
	
	public Map<String, Object> getAllTicket();
	
	public Map<String, Object> getFilteredTickets(Long assignedToUser,Long customerId,Long statusId);
	
	public Map<String, Object> getTicket(Long ticketId);
	
	public Map<String, Object> updateTicket1(Ticket ticket);
	
	public Map<String, Object> updateTicket(Map<String,Object> inputData);
	
	public Map<String, Object> updateStatus(Long ticketId,Long statusId);
	
	public Map<String, Object> assignTicket(Long ticketId,Long assignedToUser);
	
	public Map<String, Object> deleteTicket(Long ticketId);
	
	public Map<String, Object> addResponse(Long ticketId,String responseText);
	
	public Map<String, Object> closeResolvedTasks(LocalDateTime dateBefore30Days) throws Exception;

}
