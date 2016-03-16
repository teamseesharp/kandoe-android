package com.example.kandoe.Model;

import java.util.ArrayList;

/**
 * Created by Michelle on 16-3-2016.
 */
public class Snapshot {
    private int Id, SessionId;
    private ArrayList<Integer> Participants, Organisers;
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

    public ArrayList<Integer> getParticipants() {
        return Participants;
    }

    public void setParticipants(ArrayList<Integer> participants) {
        Participants = participants;
    }

    public ArrayList<Integer> getOrganisers() {
        return Organisers;
    }

    public void setOrganisers(ArrayList<Integer> organisers) {
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
