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


import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long statusId;
	
	private String statusName;
	
	
	@JsonBackReference(value="ticketsRef")
	@OneToMany(
            mappedBy = "status",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Ticket> tickets = new ArrayList<>();

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", statusName=" + statusName + ", tickets=" + tickets + "]";
	}
	
	

}
