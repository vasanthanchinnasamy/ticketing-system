package com.innovate.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	private String userName;
	
	
	@JsonBackReference(value="assignedTicketsRef")
	@OneToMany(
            mappedBy = "assignedToUser",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Ticket> assignedTickets = new ArrayList<>();
	
	
	@JsonBackReference(value="createdTicketsRef")
	@OneToMany(
            mappedBy = "createdByUser",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Ticket> createdTickets = new ArrayList<>();
	
	@Transient
	private long assignedTicketCount;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Ticket> getAssignedTickets() {
		return assignedTickets;
	}

	public void setAssignedTickets(List<Ticket> assignedTickets) {
		this.assignedTickets = assignedTickets;
	}

	public List<Ticket> getCreatedTickets() {
		return createdTickets;
	}

	public void setCreatedTickets(List<Ticket> createdTickets) {
		this.createdTickets = createdTickets;
	}

	public long getAssignedTicketCount() {
		return assignedTicketCount;
	}

	public void setAssignedTicketCount(long assignedTicketCount) {
		this.assignedTicketCount = assignedTicketCount;
	}
	
	
	
}
