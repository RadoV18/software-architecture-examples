package com.radov18.user.amqp.consumer;

import com.radov18.user.amqp.dto.UserMessageDto;
import com.radov18.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegisterConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UserRegisterConsumer.class);

    private final UserService userService;

    @RabbitListener(queues = "user-activity")
    public void consume(UserMessageDto message) {
        logger.info("Received {} message: {}", message.getType(), message.getKeycloakId());
        if(message.getType().equals("REGISTER")) {
            userService.createUser(message);
        }
    }
}
