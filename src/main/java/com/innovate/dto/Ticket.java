package com.innovate.dto;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

@Entity
@Where(clause = "delete_key = false")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ticketId;
	
	private Long type;
	
	private String title;
	
	private String description;
	
	private String createdByUser;
	
	private Long customerId;
	
	private String assignedToUser;
	
	private Long priorityId;
	
	
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "priority_id", referencedColumnName = "priorityId")
//	private Priority priority;
	
	private Long statusId;
	
	@Basic
	private LocalDateTime statusUpdatedAt;	
	
	private Boolean deleteKey = Boolean.FALSE;

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

	public String getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getAssignedToUser() {
		return assignedToUser;
	}

	public void setAssignedToUser(String assignedToUser) {
		this.assignedToUser = assignedToUser;
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
	
//	public Priority getPriority() {
//		return priority;
//	}
//
//	public void setPriority(Priority priority) {
//		this.priority = priority;
//	}

	public Ticket(Long ticketId, Long type, String title, String description, String createdByUser, Long customerId,
			String assignedToUser, Long priorityId, Long statusId, LocalDateTime statusUpdatedAt, Boolean deleteKey) {
		super();
		this.ticketId = ticketId;
		this.type = type;
		this.title = title;
		this.description = description;
		this.createdByUser = createdByUser;
		this.customerId = customerId;
		this.assignedToUser = assignedToUser;
//		this.priorityId = priorityId;
		this.statusId = statusId;
		this.statusUpdatedAt = statusUpdatedAt;
		this.deleteKey = deleteKey;
	}

	public Ticket() {
		super();		
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", type=" + type + ", title=" + title + ", description=" + description
				+ ", createdByUser=" + createdByUser + ", customerId=" + customerId + ", assignedToUser="
				+ assignedToUser + ", priorityId=" 
//				+ priorityId 
				+ ", statusId=" + statusId + ", statusUpdatedAt="
				+ statusUpdatedAt + ", deleteKey=" + deleteKey + "]";
	}
	
//	public Ticket copyTicket(Ticket copyTicket) {
//		this.ticketId = ticketId;
//		this.type = type;
//		this.title = title;
//		this.description = description;
//		this.createdByUser = createdByUser;
//		this.customerId = customerId;
//		this.assignedToUser = assignedToUser;
//		this.priorityId = priorityId;
//		this.statusId = statusId;
//		this.statusUpdatedAt = statusUpdatedAt;
//		this.deleteKey = deleteKey;
//	}

}
