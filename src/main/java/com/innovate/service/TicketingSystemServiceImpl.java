package com.innovate.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import utility.EmailUtility;

import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovate.configuration.MessagingConfig;
import com.innovate.dao.TicketDao;
import com.innovate.dto.Response;
import com.innovate.dto.Ticket;

@Service
public class TicketingSystemServiceImpl implements TicketingSystemService{
	
	@Autowired
	private TicketDao ticketDao;
	
    @Autowired
    private RabbitTemplate template;
	
	@Override
	public Map<String, Object> createTicket(Ticket ticket) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("result", ticketDao.addTicket(ticket));
		}catch(Exception exception) {
			resultMap.put("error", exception);
			resultMap.put("status", Boolean.FALSE);
		}
		resultMap.put("status", Boolean.TRUE);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getAllTicket() {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("result", ticketDao.getAllTicket());
		}catch(Exception exception) {
			resultMap.put("error", exception);
			resultMap.put("status", Boolean.FALSE);
		}
		resultMap.put("status", Boolean.TRUE);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getFilteredTickets(String assignedToUser, Long customerId, Long statusId) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("result", ticketDao.getFilteredTickets(assignedToUser, customerId, statusId));
		}catch(Exception exception) {
			resultMap.put("error", exception);
			resultMap.put("status", Boolean.FALSE);
		}
		resultMap.put("status", Boolean.TRUE);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getTicket(Long ticketId) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("result", ticketDao.getTicket(ticketId));
		}catch(Exception exception) {
			resultMap.put("error", exception);
			resultMap.put("status", Boolean.FALSE);
		}
		resultMap.put("status", Boolean.TRUE);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> updateTicket1(Ticket ticket) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("result", ticketDao.updateTicket(ticket));
		}catch(Exception exception) {
			resultMap.put("error", exception);
			resultMap.put("status", Boolean.FALSE);
		}
		resultMap.put("status", Boolean.TRUE);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> updateStatus(Long ticketId, Long statusId) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("result", ticketDao.updateStatus(ticketId, statusId));
		}catch(Exception exception) {
			resultMap.put("error", exception);
			resultMap.put("status", Boolean.FALSE);
		}
		resultMap.put("status", Boolean.TRUE);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> assignTicket(Long ticketId, String assignedToUser) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("result", ticketDao.assignTicket(ticketId, assignedToUser));
		}catch(Exception exception) {
			resultMap.put("error", exception);
			resultMap.put("status", Boolean.FALSE);
		}
		resultMap.put("status", Boolean.TRUE);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteTicket(Long ticketId) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("result", ticketDao.deleteTicket(ticketId));
		}catch(Exception exception) {
			resultMap.put("error", exception);
			resultMap.put("status", Boolean.FALSE);
		}
		resultMap.put("status", Boolean.TRUE);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> addResponse(Long ticketId, String responseText) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("result", ticketDao.addResponse(ticketId, responseText));
			template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, new Response(responseText,ticketId,null));
			
		}catch(Exception exception) {
			resultMap.put("error", exception);
			resultMap.put("status", Boolean.FALSE);
		}
		resultMap.put("status", Boolean.TRUE);
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> updateTicket(Map<String, Object> inputData) {
		JSONObject jsonObject = new JSONObject(inputData);
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("result", ticketDao.updateTicket(jsonObject.getLong("ticketId"), inputData));
		}catch(Exception exception) {
			resultMap.put("error", exception);
			resultMap.put("status", Boolean.FALSE);
		}
		resultMap.put("status", Boolean.TRUE);
		
		return resultMap;
	}

}
