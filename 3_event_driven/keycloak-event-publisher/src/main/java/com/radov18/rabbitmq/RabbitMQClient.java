package com.radov18.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class RabbitMQClient {

    private final String rabbitHost;
    private final String rabbitPort;
    private final String rabbitUser;
    private final String rabbitPassword;
    private final String virtualHost;
    private Connection connection;

    public RabbitMQClient(String rabbitHost, String rabbitPort, String rabbitUser, String rabbitPassword, String virtualHost) {
        this.rabbitHost = rabbitHost;
        this.rabbitPort = rabbitPort;
        this.rabbitUser = rabbitUser;
        this.rabbitPassword = rabbitPassword;
        this.virtualHost = virtualHost;
        initConnection();
    }

    private void initConnection() {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(rabbitHost);
            connectionFactory.setPassword(rabbitPassword);
            connectionFactory.setUsername(rabbitUser);
            connectionFactory.setPort(Integer.parseInt(rabbitPort));
            connectionFactory.setVirtualHost(virtualHost);

            this.connection = connectionFactory.newConnection();
        } catch(Exception e) {
            throw new RuntimeException("Error connecting to RabbitMQ", e);
        }
    }

    public void sendToQueue(String exchange, String routingKey, String message) throws IOException {
        connection.createChannel().basicPublish(exchange, routingKey, null, message.getBytes());
    }

}
