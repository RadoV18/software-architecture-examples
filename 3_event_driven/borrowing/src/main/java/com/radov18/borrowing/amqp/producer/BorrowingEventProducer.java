package com.radov18.borrowing.amqp.producer;

import com.radov18.borrowing.amqp.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowingEventProducer {

    private static final Logger logger = LoggerFactory.getLogger(BorrowingEventProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public <T> void sendMessage(String exchange, String routingKey, MessageDto<T> message) {
        logger.info("Sending message of type {} to exchange {} with routing key {}", message.getType(), exchange, routingKey);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
