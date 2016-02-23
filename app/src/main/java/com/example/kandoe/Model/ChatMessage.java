package com.example.kandoe.Model;

import org.joda.time.DateTime;

/**
 * Created by Michelle on 22-2-2016.
 */
public class ChatMessage {
    private String message;
    private DateTime timestamp;

    public ChatMessage(String message, DateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }
}
