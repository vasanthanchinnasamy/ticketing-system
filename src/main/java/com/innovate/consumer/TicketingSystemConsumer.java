package com.innovate.consumer;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.innovate.configuration.MessagingConfig;
import com.innovate.dto.Response;
import com.innovate.dto.Ticket;
import com.innovate.entity.MessageEntity;
import com.innovate.service.TicketingSystemService;

import utility.EmailUtility;

@Component	
public class TicketingSystemConsumer {
	
	@Autowired
	private TicketingSystemService ticketingSystemService;
	
    @Autowired
    private RabbitTemplate template;
	
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Response response) throws IOException {
        System.out.println("Message recieved from queue : " + response);
			EmailUtility.sendResponseMail(response.getTicketId(), response.getResponseText());
    }
    
    @RabbitListener(queues = MessagingConfig.CLOSE_TASK_QUEUE)
    public void consumeMessageFromCloseTaskQueue(MessageEntity message) throws IOException {
        System.out.println("Message recieved from queue : " + message);
        LocalDateTime dateBefore30Days = LocalDateTime.of(message.getYear(), message.getMonth(), message.getDate(), 0, 0);
        ticketingSystemService.closeResolvedTasks(dateBefore30Days);
    }

}
