package com.example.kandoe.Model;

public class ChatMessage {
    private int Id,MessengerId,SessionId;
    private String Text;
    private String Timestamp;

    public ChatMessage() {}

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

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.Timestamp = timestamp;
    }
}
