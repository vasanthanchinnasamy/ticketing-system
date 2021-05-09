package com.innovate.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String EMAIL_QUEUE = "ticketingsystem_email_queue";
    public static final String CLOSE_TASK_QUEUE = "ticketingsystem_close_task_queue";
    public static final String EXCHANGE = "ticketingsystem_exchange";
    public static final String EMAIL_ROUTING_KEY = "ticketingsystem_routingKey";
    public static final String CLOSE_TASK_ROUTING_KEY = "ticketingsystem_closetask_routingKey";

    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE);
    }
    
    @Bean
    public Queue closeTaskQueue() {
        return new Queue(CLOSE_TASK_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding emailBinding(@Qualifier("emailQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(EMAIL_ROUTING_KEY);
    }
    
    @Bean
    public Binding closeTaskBinding(@Qualifier("closeTaskQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(CLOSE_TASK_ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}