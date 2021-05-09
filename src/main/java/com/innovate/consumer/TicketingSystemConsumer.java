package com.innovate.consumer;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.innovate.configuration.MessagingConfig;
import com.innovate.dto.Response;

import utility.EmailUtility;

@Component	
public class TicketingSystemConsumer {
	
    @Autowired
    private RabbitTemplate template;
	
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Response response) throws IOException {
        System.out.println("Message recieved from queue : " + response);
			EmailUtility.sendResponseMail(response.getTicketId(), response.getResponseText());
    }

}
