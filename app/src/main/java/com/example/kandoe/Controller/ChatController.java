package com.example.kandoe.Controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Thomas on 2016-02-26.
 */
public class ChatController {

    private MessageSender messageSender;
    private MessageReceiver messageReceiver;

    public ChatController(int sessionId) {
        try {
            this.messageSender = new MessageSender(sessionId);
            this.messageReceiver = new MessageReceiver(sessionId);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String message){
        try {
            messageSender.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
