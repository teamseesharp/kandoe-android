package com.example.kandoe.Model;

import java.util.ArrayList;

public class Snapshot {
    private int Id, SessionId;
    private ArrayList<UserAccount> Participants, Organisers;
    private ArrayList<Card> SessionCards;
    private ArrayList<ChatMessage> ChatMessages;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSessionId() {
        return SessionId;
    }

    public void setSessionId(int sessionId) {
        SessionId = sessionId;
    }

    public ArrayList<UserAccount> getParticipants() {
        return Participants;
    }

    public void setParticipants(ArrayList<UserAccount> participants) {
        Participants = participants;
    }

    public ArrayList<UserAccount> getOrganisers() {
        return Organisers;
    }

    public void setOrganisers(ArrayList<UserAccount> organisers) {
        Organisers = organisers;
    }

    public ArrayList<Card> getSessionCards() {
        return SessionCards;
    }

    public void setSessionCards(ArrayList<Card> sessionCards) {
        SessionCards = sessionCards;
    }

    public ArrayList<ChatMessage> getChatMessages() {
        return ChatMessages;
    }

    public void setChatMessages(ArrayList<ChatMessage> chatMessages) {
        ChatMessages = chatMessages;
    }
}
