package com.innovate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.innovate.dto.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long>{
	
//	Ticket findByTeamName(String teamName);

}
