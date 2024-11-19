package com.radov18.provider;

import com.radov18.dto.UserMessageDto;
import com.radov18.rabbitmq.RabbitMQClient;
import com.radov18.utils.JsonUtils;
import org.jboss.logging.Logger;
import org.keycloak.models.AbstractKeycloakTransaction;

public class UserActivityTransaction extends AbstractKeycloakTransaction {

    private static final Logger logger = Logger.getLogger(UserActivityTransaction.class);

    private final RabbitMQClient rabbitMQClient;
    private final UserMessageDto userMessageDto;

    public UserActivityTransaction(RabbitMQClient rabbitMQClient, UserMessageDto userMessageDto) {
        this.rabbitMQClient = rabbitMQClient;
        this.userMessageDto = userMessageDto;
    }

    @Override
    protected void commitImpl() {
        try {
            rabbitMQClient.sendToQueue("keycloak", "user-activity", JsonUtils.toJson(userMessageDto));
        } catch(Exception e) {
            logger.error("Error sending message to RabbitMQ", e);
        }
    }

    @Override
    protected void rollbackImpl() {

    }
}
