package com.innovate.configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.innovate.entity.MessageEntity;

@Configuration
@EnableScheduling
public class ScheduleConfig {
	
	
    @Autowired
    private RabbitTemplate template;
	
	
	
	@Scheduled(cron = "${cron.expression}")
	public void closeResolvedTasks() {
		LocalDateTime dateBefore30Days = LocalDate.now().minusDays(30).atTime(0, 0);
		MessageEntity message = new MessageEntity();
		message.setDate(dateBefore30Days.getDayOfMonth());
		message.setMonth(dateBefore30Days.getMonthValue());
		message.setYear(dateBefore30Days.getYear());
		template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.CLOSE_TASK_ROUTING_KEY, message);
	}

}
