package com.innovate.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innovate.dto.Response;
import com.innovate.dto.Status;
import com.innovate.dto.Ticket;
import com.innovate.service.TicketingSystemService;

@RestController
@RequestMapping("/ticketingsystem")
public class TicketingSystemController {
	
	@Autowired
	private TicketingSystemService ticketingSystemService;
	
	@PostMapping("/createTicket")  
	public Map<String, Object> createTicket(@RequestBody Ticket ticket) {
		return ticketingSystemService.createTicket(ticket);
	}
	
	@GetMapping("/getAllTickets") 
	public Map<String, Object> getAllTickets() {
		return ticketingSystemService.getAllTicket();
	}
	
	
	@GetMapping("/getFilteredTickets")
	public Map<String, Object> getFilteredTickets(@RequestBody Ticket ticket){
		return ticketingSystemService.getFilteredTickets(ticket.getAssignedToUserId(), ticket.getCustomerId(), ticket.getStatusId());
	}
	
	@GetMapping("/getTicket/{ticketId}")
	public Map<String, Object> getTicket(@PathVariable Long ticketId) {
		return ticketingSystemService.getTicket(ticketId);
	}
	
	@PutMapping("/updateTicket1")
	public Map<String, Object> updateTicket1(@RequestBody Ticket ticket) {
		return ticketingSystemService.updateTicket1(ticket);
	}
	
	@PutMapping("/updateTicket")
	public Map<String, Object> updateTicket(@RequestBody Map<String,Object> inputData) {
		return ticketingSystemService.updateTicket(inputData);
	}
	
	@PutMapping("/updateStatus")
	public Map<String, Object> updateStatus(@RequestBody Ticket ticket) {
		return ticketingSystemService.updateStatus(ticket.getTicketId(), ticket.getStatusId());
	}
	
	@PutMapping("/assignTicket")
	public Map<String, Object> assignTicket(@RequestBody Ticket ticket) {
		return ticketingSystemService.assignTicket(ticket.getTicketId(), ticket.getAssignedToUserId());
	}
	
	@PutMapping("/deleteTicket/{ticketId}")
	public Map<String, Object> deleteTicket(@PathVariable Long ticketId) {
		return ticketingSystemService.deleteTicket(ticketId);
	}
	
	@PostMapping("/addResponse")
	public Map<String, Object> addResponse(@RequestBody Response response) {
		return ticketingSystemService.addResponse(response.getTicketId(), response.getResponseText());
	}
	
	

}
