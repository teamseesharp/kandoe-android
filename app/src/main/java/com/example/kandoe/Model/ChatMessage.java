package com.example.kandoe.Model;

import org.joda.time.DateTime;

/**
 * Created by Michelle on 22-2-2016.
 */
public class ChatMessage {
    private int Id,MessengerId,SessionId;
    private String Text;
    private DateTime timestamp;

    public ChatMessage(int id, int messengerId, int sessionId, String text, DateTime timestamp) {
        Id = id;
        MessengerId = messengerId;
        SessionId = sessionId;
        Text = text;
        this.timestamp = timestamp;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getMessengerId() {
        return MessengerId;
    }

    public void setMessengerId(int messengerId) {
        MessengerId = messengerId;
    }

    public int getSessionId() {
        return SessionId;
    }

    public void setSessionId(int sessionId) {
        SessionId = sessionId;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }
}
