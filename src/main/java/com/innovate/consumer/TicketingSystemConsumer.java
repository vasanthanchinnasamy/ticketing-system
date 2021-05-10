package com.innovate.consumer;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.innovate.configuration.MessagingConfig;
import com.innovate.entity.MessageEntity;
import com.innovate.service.TicketingSystemService;

import utility.EmailUtility;

@Component	
public class TicketingSystemConsumer {
	
	@Autowired
	private TicketingSystemService ticketingSystemService;
	
    @RabbitListener(queues = MessagingConfig.EMAIL_QUEUE)
    public void consumeMessageFromEmailQueue(MessageEntity response) throws IOException {
		EmailUtility.sendResponseMail(response.getTicketId(), response.getResponseText(),response.getEmailAddress());
    }
    
    @RabbitListener(queues = MessagingConfig.CLOSE_TASK_QUEUE)
    public void consumeMessageFromCloseTaskQueue(MessageEntity message) throws Exception {
        LocalDateTime dateBefore30Days = LocalDateTime.of(message.getYear(), message.getMonth(), message.getDate(), 0, 0);
        ticketingSystemService.closeResolvedTasks(dateBefore30Days);
    }

}
