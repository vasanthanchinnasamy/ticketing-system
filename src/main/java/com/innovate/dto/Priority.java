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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Priority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long priorityId;
	
	private String priorityName;
	
	
	@JsonBackReference(value="priorityRef")
	@OneToMany(
            mappedBy = "priority",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Ticket> tickets = new ArrayList<>();

	public long getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(long priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "Priority [priorityId=" + priorityId + ", priorityName=" + priorityName + ", tickets=" + tickets + "]";
	}

	
	
}
