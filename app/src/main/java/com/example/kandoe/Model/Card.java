package com.example.kandoe.Model;

import java.util.ArrayList;

/**
 * Created by Michelle on 22-2-2016.
 */

public class Card {
    private int Id,CreatorId;
    private String Text;
    private String Description;
    private String SubthemeId;
    private String Image;

    private ArrayList<SubTheme> Subthemes;
    private ArrayList<Session> Sessions;
    private ArrayList<CardReview> cardReviews;

    public Card() {
    }

    public Card(int id, String text, String subthemeId, String image) {
        Id = id;
        Text = text;
        SubthemeId = subthemeId;
        Image = image;
        this.Description = "Geen beschrijving";
    }

    public Card(int id, int creatorId, String text, String description, String subthemeId, String image, ArrayList<SubTheme> subthemes, ArrayList<Session> sessions, ArrayList<CardReview> cardReviews) {
        Id = id;
        CreatorId = creatorId;
        Text = text;
        Description = description;
        SubthemeId = subthemeId;
        Image = image;
        Subthemes = subthemes;
        Sessions = sessions;
        this.cardReviews = cardReviews;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        this.Text = text;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getSubthemeId() {
        return SubthemeId;
    }

    public void setSubthemeId(String subthemeId) {
        SubthemeId = subthemeId;
    }

    public int getCreatorId() {
        return CreatorId;
    }

    public void setCreatorId(int creatorId) {
        CreatorId = creatorId;
    }

    public ArrayList<SubTheme> getSubthemes() {
        return Subthemes;
    }

    public void setSubthemes(ArrayList<SubTheme> subthemes) {
        Subthemes = subthemes;
    }

    public ArrayList<Session> getSessions() {
        return Sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        Sessions = sessions;
    }

    public ArrayList<CardReview> getCardReviews() {
        return cardReviews;
    }

    public void setCardReviews(ArrayList<CardReview> cardReviews) {
        this.cardReviews = cardReviews;
    }
}


