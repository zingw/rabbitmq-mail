package com.example.emailpublisher.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String MAIL_EXCHANGE = "MAIL_EXCHANGE";

    public static final String MAIL_QUEUE = "MAIL_QUEUE";
    public static final String ROUTING_KEY = "1234";

    @Bean
    Queue queue() {
        return new Queue(MAIL_QUEUE, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(MAIL_EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    AmqpTemplate container(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
