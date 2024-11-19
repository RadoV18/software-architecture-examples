package com.radov18.notification.amqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    /**
     * Exchange that uses topic routing.
     * Topic routing is a flexible way to route messages to queues based on wildcard matches
     * between the routing key and the routing pattern specified in the binding.
     */
    @Bean
    public TopicExchange borrowingExchange() {
        return new TopicExchange("borrowing-exchange");
    }

    /**
     * Queue that will be used to receive messages from the borrowing exchange.
     */
    @Bean
    public Queue borrowingQueue() {
        return new Queue("notification-borrowing-queue", true);
    }

    /**
     * Binding between the borrowing queue and the borrowing exchange.
     * This queue will receive messages that are sent to the borrowing-exchange with the routing key borrowing.
     */
    @Bean
    public Binding borrowingBinding(Queue bookBorrowingQueue, TopicExchange borrowingExchange) {
        return BindingBuilder.bind(bookBorrowingQueue).to(borrowingExchange).with("*.borrowing");
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
