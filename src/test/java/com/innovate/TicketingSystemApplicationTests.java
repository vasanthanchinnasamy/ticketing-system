package com.innovate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.innovate.controller.TicketingSystemController;
import com.innovate.dao.TicketDao;
import com.innovate.dto.Priority;
import com.innovate.dto.Status;
import com.innovate.dto.Ticket;
import com.innovate.dto.User;
import com.innovate.service.TicketingSystemService;

@SpringBootTest
class TicketingSystemApplicationTests {
	
	@Autowired
	private TicketDao ticketDao;
	
	@Autowired
	private TicketingSystemController ticketingSystemController;
	
	

	@Test
	void contextLoads() {
		
		Ticket ticket = new Ticket();
		ticket.setTitle("Priority Check 1");
		Priority p = new Priority();
		p.setPriorityId(1);
		ticket.setPriority(p);
		
		Status status = new Status();
		status.setStatusId(1);
		ticket.setStatus(status);
		
		User user = new User();
		user.setUserId(1);
		ticket.setCreatedByUser(user);
		
		User user1 = new User();
		user1.setUserId(2);
		ticket.setAssignedToUser(user1);
		
				
		ticketDao.addTicket(ticket);
		
//		Ticket ticket2 = new Ticket();
//		ticket2.setTitle("132 update check"+ ZonedDateTime.now());
////		ticket2.setCreatedByUser("Vasanthan 123");
//		ticket2.setTicketId(1L);
//		ticketDao.updateTicket(ticket2);
//		
//		List<Ticket> s = ticketDao.getAllTicket();
//		List<Ticket> s1 = ticketDao.getFilteredTickets("vas", 1L, null);
//		List<Ticket> s2 = ticketDao.getFilteredTickets(null, null, null);
//		Ticket ticket1 = ticketDao.getTicket(1L);
//		ticketDao.updateStatus(1L, 5L);
//		ticketDao.deleteTicket(1L);
		System.out.println(ticket);
	}
	
	
	@Test
	void getAllTickets() {
		Map<String, Object> resultMap = ticketingSystemController.getAllTickets();
		System.out.println(resultMap);
		
	}
	
	@Test
	void closeResolvedTasks() {
		LocalDateTime dateBefore30Days = LocalDate.now().minusDays(30).atTime(0, 0);
		int ticketList = ticketDao.closeResolvedTasks(dateBefore30Days);
		System.out.println(ticketList);
		
	}
	
	@Test
	void assignTicketBasedOnLoad() {
		Optional<Object> ticketList = ticketDao.assignTicketBasedOnLoad();
		System.out.println(ticketList);
		
	}
	
	

}
