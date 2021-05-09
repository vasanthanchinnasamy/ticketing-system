package com.innovate;

import java.time.ZonedDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.innovate.dao.TicketDao;
import com.innovate.dto.Priority;
import com.innovate.dto.Ticket;
import com.innovate.service.TicketingSystemService;

@SpringBootTest
class TicketingSystemApplicationTests {
	
	@Autowired
	private TicketDao ticketDao;

	@Test
	void contextLoads() {
		
		Ticket ticket = new Ticket();
		ticket.setTitle("Priority Check 3");
		Priority p = new Priority();
//		p.setPriorityName("High");
		p.setPriorityId(29);
//		ticket.setPriority(p);
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

}
