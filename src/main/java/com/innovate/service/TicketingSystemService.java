package com.innovate.service;

import java.util.List;
import java.util.Map;

import com.innovate.dto.Response;
import com.innovate.dto.Ticket;

public interface TicketingSystemService {
	
	
	public Map<String, Object> createTicket(Ticket ticket);
	
	public Map<String, Object> getAllTicket();
	
	public Map<String, Object> getFilteredTickets(String assignedToUser,Long customerId,Long statusId);
	
	public Map<String, Object> getTicket(Long ticketId);
	
	public Map<String, Object> updateTicket1(Ticket ticket);
	
	public Map<String, Object> updateTicket(Map<String,Object> inputData);
	
	public Map<String, Object> updateStatus(Long ticketId,Long statusId);
	
	public Map<String, Object> assignTicket(Long ticketId,String assignedToUser);
	
	public Map<String, Object> deleteTicket(Long ticketId);
	
	public Map<String, Object> addResponse(Long ticketId,String responseText);

}
