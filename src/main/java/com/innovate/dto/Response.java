package com.innovate.dto;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Response {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long responseId;
	
	private String responseText;
	
	@Transient
	private Long ticketId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
	private Ticket ticket;
	
	@Basic
	private LocalDateTime updatedAt;
	
	public Long getResponseId() {
		return responseId;
	}

	public void setResponseId(Long responseId) {
		this.responseId = responseId;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	
	public Long getTicketId() {
		return ticketId;
	}
	
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Response(String responseText, Long ticketId, LocalDateTime updatedAt) {
		super();
		this.responseText = responseText;
		Ticket ticket = new Ticket();
		ticket.setTicketId(ticketId);
		this.ticket = ticket;
		this.updatedAt = updatedAt;
	}	
	
	public Response() {
		super();
	}	
	

}
