package com.radov18.provider;

import com.radov18.rabbitmq.RabbitMQClient;
import org.jboss.logging.Logger;
import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class UserRegisterEventListenerProviderFactory implements EventListenerProviderFactory {

    private static final Logger logger = Logger.getLogger(UserRegisterEventListenerProviderFactory.class);

    private RabbitMQClient rabbitMQClient;

    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {
        return new UserRegisterEventListenerProvider(rabbitMQClient, keycloakSession);
    }

    @Override
    public void init(Config.Scope scope) {
        String rabbitHost = scope.get("rabbitHost");
        String rabbitPort = scope.get("rabbitPort");
        String rabbitUser = scope.get("rabbitUser");
        String rabbitPassword = scope.get("rabbitPassword");
        String virtualHost = scope.get("virtualHost");
        logger.info("Starting RabbitMQ client");
        this.rabbitMQClient = new RabbitMQClient(rabbitHost, rabbitPort, rabbitUser, rabbitPassword, virtualHost);
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return "user-register-event-listener";
    }
}
