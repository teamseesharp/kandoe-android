package com.example.kandoe.Controller;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Thomas on 2016-02-26.
 */
public class MessageSender {

    private String exchange_name;
    private Channel channel;
    private Connection connection;

    public MessageSender(int sessionId) throws IOException, TimeoutException {
        this.exchange_name = setExchange_name(sessionId);
        //init();

    }

    public void sendMessage(String message) throws IOException {
        channel.basicPublish(exchange_name, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }

    private String setExchange_name(int sessionId) {
        return "session" + sessionId + "exchange";

    }

    public void close() throws IOException, TimeoutException {

        channel.close();
        connection.close();
    }

    private void init() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("chicken.rmq.cloudamqp.com");
        factory.setVirtualHost("ikzklxjf");
        factory.setUsername("ikzklxjf");
        factory.setPassword("9nmUZu4Py2DtS_LgfNkjcpL-d0NSWLkU");

        connection = factory.newConnection();
        channel = connection.createChannel();

        channel.exchangeDeclare(exchange_name, "fanout");


    }
}
