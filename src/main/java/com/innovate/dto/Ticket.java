package com.innovate.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Where(clause = "delete_key = false")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "ticketId") 
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ticketId;
	
	private Long type;
	
	private String title;
	
	private String description;
	
//	@JsonManagedReference(value="createdByUserRef")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_user_id")
	private User createdByUser;
	
	private Long customerId;
	
//	@JsonManagedReference(value="assignedToUserRef")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_to_user_id")
	private User assignedToUser;
	
//	@JsonManagedReference(value="priorityRef")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priority_id")
	private Priority priority;
	
//	@JsonManagedReference(value="statusRef")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
	private Status status;
	
	@Basic
	private LocalDateTime statusUpdatedAt;	
	
	private Boolean deleteKey = Boolean.FALSE;
	
	@OneToMany(
            mappedBy = "ticket",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Response> responses = new ArrayList<>();
	
	
	@Transient
	private Long priorityId;
	@Transient
	private Long statusId;
	@Transient
	private Long assignedToUserId;
	@Transient
	private Long createdByUserId;

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public LocalDateTime getStatusUpdatedAt() {
		return statusUpdatedAt;
	}

	public void setStatusUpdatedAt(LocalDateTime statusUpdatedAt) {
		this.statusUpdatedAt = statusUpdatedAt;
	}

	public Boolean getDeleteKey() {
		return deleteKey;
	}

	public void setDeleteKey(Boolean deleteKey) {
		this.deleteKey = deleteKey;
	}
	
	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	public User getAssignedToUser() {
		return assignedToUser;
	}

	public void setAssignedToUser(User assignedToUser) {
		this.assignedToUser = assignedToUser;
	}

	public Ticket() {
		super();		
	}
	

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	public Long getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Long priorityId) {
		this.priorityId = priorityId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getAssignedToUserId() {
		return assignedToUserId;
	}

	public void setAssignedToUserId(Long assignedToUserId) {
		this.assignedToUserId = assignedToUserId;
	}

	public Long getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(Long createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", type=" + type + ", title=" + title + ", description=" + description
				+ ", createdByUser=" + createdByUser + ", customerId=" + customerId + ", assignedToUser="
				+ assignedToUser + ", priorityId=" 
//				+ priorityId 
				+ ", statusId=" 
//				+ statusId 
				+ ", statusUpdatedAt="
				+ statusUpdatedAt + ", deleteKey=" + deleteKey + "]";
	}
	

}
