package com.innovate.entity;


public class MessageEntity {
	
	
	private int year;
	
	private int month;
	
	private int date;
	
	private Long ticketId;
	
	private String responseText;	
	
	private String emailAddress;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public MessageEntity(int year, int month, int date) {
		super();
		this.year = year;
		this.month = month;
		this.date = date;
	}
	
	
	public MessageEntity(String responseText, Long ticketId, String emailAddress) {
		super();
		this.responseText = responseText;
		this.ticketId = ticketId;
		this.emailAddress = emailAddress;
	}

	public MessageEntity() {
		super();
	}
	
	
	
}
