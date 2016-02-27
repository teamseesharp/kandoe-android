package com.example.kandoe.Controller;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Thomas on 2016-02-26.
 */
public class MessageReceiver {

    private String exchange_name;
    private Channel channel;
    private Connection connection;

    public MessageReceiver(int sessionId) throws IOException, TimeoutException {
        this.exchange_name = setExchange_name(sessionId);
        init();

    }



    private String setExchange_name(int sessionId) {
        return "session" + sessionId + "exchange";

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

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchange_name, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);

    }

}
