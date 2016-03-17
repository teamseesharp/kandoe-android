package com.example.kandoe.Model;

/**
 * Created by Michelle on 22-2-2016.
 */
public class ChatMessage {
    private int Id,MessengerId,SessionId;
    private Integer SnapshotId;
    private String Text;
    private String Timestamp;

    public ChatMessage() {}

    public ChatMessage(int id, int messengerId, int sessionId, String text, String timestamp) {
        Id = id;
        MessengerId = messengerId;
        SessionId = sessionId;
        Text = text;
        this.Timestamp = timestamp;
    }

    public ChatMessage(int id, int messengerId, int sessionId, int snapshotId, String text, String timestamp) {
        Id = id;
        MessengerId = messengerId;
        SessionId = sessionId;
        SnapshotId = snapshotId;
        Text = text;
        Timestamp = timestamp;
    }

    public ChatMessage(int messengerId, int sessionId, String text) {
        MessengerId = messengerId;
        SessionId = sessionId;
        Text = text;
    }

    public ChatMessage(int id,int messengerId, int sessionId, String text) {
        Id = id;
        MessengerId = messengerId;
        SessionId = sessionId;
        Text = text;
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

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.Timestamp = timestamp;
    }

    public Integer getSnapshotId() {
        return SnapshotId;
    }

    public void setSnapshotId(Integer snapshotId) {
        SnapshotId = snapshotId;
    }
}
