package com.example.kandoe.Model;

import android.accounts.Account;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Michelle on 20-2-2016.
 */
public class Session implements Serializable{

    private int Id, MaxCardsToChoose, MaxParticipants, Modus, OrganisationId, Round, SubthemeId;
    private boolean CardCreationAllowed, CardReviewsAllowed, IsFinished;
    private DateTime End,Start;
    private int NumberOfSteps;

    private ArrayList<Card> Cards;
    private ArrayList<ChatMessage> Chatmessages;
    private ArrayList<Account> Organisers;
    private ArrayList<Account> Participants;


    public Session() {
        MaxParticipants = 8;
        MaxCardsToChoose = 3;
    }

    public Session(int id, int maxCardsToChoose, int maxParticipants, int modus, int organisationId, int round, int subthemeId, boolean cardCreationAllowed, boolean cardReviewsAllowed, boolean isFinished, DateTime end, DateTime start, ArrayList<Card> cards, ArrayList<ChatMessage> chatmessages, ArrayList<Account> organisers, ArrayList<Account> participants) {
        Id = id;
        MaxCardsToChoose = maxCardsToChoose;
        MaxParticipants = maxParticipants;
        Modus = modus;
        OrganisationId = organisationId;
        Round = round;
        SubthemeId = subthemeId;
        CardCreationAllowed = cardCreationAllowed;
        CardReviewsAllowed = cardReviewsAllowed;
        IsFinished = isFinished;
        End = end;
        Start = start;
        Cards = cards;
        Chatmessages = chatmessages;
        Organisers = organisers;
        Participants = participants;
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

    public boolean isCardReviewsAllowed() {
        return CardReviewsAllowed;
    }

    public void setCardReviewsAllowed(boolean cardReviewsAllowed) {
        CardReviewsAllowed = cardReviewsAllowed;
    }

    public boolean isFinished() {
        return IsFinished;
    }

    public void setIsFinished(boolean isFinished) {
        IsFinished = isFinished;
    }

    public DateTime getEnd() {
        return End;
    }

    public void setEnd(DateTime end) {
        End = end;
    }

    public DateTime getStart() {
        return Start;
    }

    public void setStart(DateTime start) {
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
        return Cards;
    }

    public void setCards(ArrayList<Card> cards) {
        Cards = cards;
    }

    public ArrayList<ChatMessage> getChatmessages() {
        return Chatmessages;
    }

    public void setChatmessages(ArrayList<ChatMessage> chatmessages) {
        Chatmessages = chatmessages;
    }

    public ArrayList<Account> getParticipants() {
        return Participants;
    }

    public void setParticipants(ArrayList<Account> participants) {
        Participants = participants;
    }

    public ArrayList<Account> getOrganisers() {
        return Organisers;
    }

    public void setOrganisers(ArrayList<Account> organisers) {
        Organisers = organisers;
    }
}
