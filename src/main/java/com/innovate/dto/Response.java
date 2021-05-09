package com.innovate.dto;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Response {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long responseId;
	
	private String responseText;
	
	private Long ticketId;
	
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

	public Response(String responseText, Long ticketId, LocalDateTime updatedAt) {
		super();
		this.responseText = responseText;
		this.ticketId = ticketId;
		this.updatedAt = updatedAt;
	}	
	
	public Response() {
		super();
	}	
	

}
