package com.example.kandoe.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Session implements Serializable{
    private int Id, MaxCardsToChoose, MaxParticipants, Modus, OrganisationId, Round, SubthemeId,CurrentPlayerIndex;
    private boolean CardCreationAllowed, IsFinished;
    private String End,Start,Description;
    private int NumberOfSteps = 4;

    private ArrayList<ChatMessage> ChatMessages;
    private ArrayList<UserAccount> Invitees;
    private ArrayList<UserAccount> Organisers;
    private ArrayList<UserAccount> Participants;
    private ArrayList<Card> SessionCards;

    public Session() {
        MaxParticipants = 8;
        MaxCardsToChoose = 3;
    }

    public int getId() {
        return Id;
    }

    public int getNumberOfCards() {
        return MaxCardsToChoose;
    }

    public void setNumberOfCards(int numberOfCards) {
        this.MaxCardsToChoose = numberOfCards;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getSubThemeId() {
        return SubthemeId;
    }

    public void setSubThemeId(int subThemaId) {
        this.SubthemeId = subThemaId;
    }

    public int getMaxCardsToChoose() {
        return MaxCardsToChoose;
    }

    public void setMaxCardsToChoose(int maxCardsToChoose) {
        MaxCardsToChoose = maxCardsToChoose;
    }

    public int getMaxParticipants() {
        return MaxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        MaxParticipants = maxParticipants;
    }

    public int getModus() {
        return Modus;
    }

    public void setModus(int modus) {
        Modus = modus;
    }

    public int getOrganisationId() {
        return OrganisationId;
    }

    public void setOrganisationId(int organisationId) {
        OrganisationId = organisationId;
    }

    public int getRound() {
        return Round;
    }

    public void setRound(int round) {
        Round = round;
    }

    public boolean isCardCreationAllowed() {
        return CardCreationAllowed;
    }

    public void setCardCreationAllowed(boolean cardCreationAllowed) {
        CardCreationAllowed = cardCreationAllowed;
    }

    public int getCurrentPlayerIndex() {
        return CurrentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        CurrentPlayerIndex = currentPlayerIndex;
    }

    public boolean isFinished() {
        return IsFinished;
    }

    public void setIsFinished(boolean isFinished) {
        IsFinished = isFinished;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public int getSubthemeId() {
        return SubthemeId;
    }

    public void setSubthemeId(int subthemeId) {
        SubthemeId = subthemeId;
    }

    public int getNumberOfSteps() {
        return NumberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        NumberOfSteps = numberOfSteps;
    }

    public ArrayList<Card> getCards() {
        return SessionCards;
    }

    public void setCards(ArrayList<Card> cards) {
        SessionCards = cards;
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

    public ArrayList<UserAccount> getInvitees() {
        return Invitees;
    }

    public void setInvitees(ArrayList<UserAccount> invitees) {
        Invitees = invitees;
    }
}
