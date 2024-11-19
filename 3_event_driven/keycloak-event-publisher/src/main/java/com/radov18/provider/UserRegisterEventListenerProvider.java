package com.radov18.provider;

import com.radov18.dto.UserMessageDto;
import com.radov18.rabbitmq.RabbitMQClient;
import org.jboss.logging.Logger;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;

public class UserRegisterEventListenerProvider implements EventListenerProvider {

    private static final Logger logger = Logger.getLogger(UserRegisterEventListenerProvider.class);
    private final RabbitMQClient rabbitMQClient;
    private final KeycloakSession keycloakSession;

    public UserRegisterEventListenerProvider(RabbitMQClient rabbitMQClient, KeycloakSession keycloakSession) {
        this.rabbitMQClient = rabbitMQClient;
        this.keycloakSession = keycloakSession;
    }

    @Override
    public void onEvent(Event event) {
        if(!event.getType().equals(EventType.REGISTER)) {
            return;
        }
        logger.info("REGISTER Event received from " + event.getUserId());
        UserActivityTransaction userActivityTransaction = new UserActivityTransaction(
                rabbitMQClient,
                new UserMessageDto(event.getUserId(), "REGISTER")
        );
        keycloakSession.getTransactionManager().enlistPrepare(userActivityTransaction);
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        // do nothing
    }

    @Override
    public void close() {
        // do nothing
    }
}
