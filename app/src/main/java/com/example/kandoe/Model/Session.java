package com.example.kandoe.Model;

import android.media.Image;
import android.os.Message;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Michelle on 20-2-2016.
 */
public class Session {


    private int Id, MaxCardsToChoose, MaxParticipants, Modus, OrganisationId, Round, SubthemeId;
    boolean CardCreationAllowed, CardReviewsAllowed, IsFinished;



    private int numberOfSteps;

    public Session() {
    }

    public int getId() {
        return Id;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
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


    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
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

    }
