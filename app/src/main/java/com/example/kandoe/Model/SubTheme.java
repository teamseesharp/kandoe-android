package com.example.kandoe.Model;

import java.util.ArrayList;

/**
 * Created by Michelle on 22-2-2016.
 */
public class SubTheme {
    private int Id,OrganiserId,ThemeId;
    private String Name;

    private ArrayList<Session> Sessions;
    private ArrayList<Card> SelectionCards;

    public SubTheme() {
    }

    public SubTheme(int id, int organiserId, int themeId, String name, ArrayList<Session> sessions, ArrayList<Card> cards) {
        Id = id;
        OrganiserId = organiserId;
        ThemeId = themeId;
        Name = name;
        Sessions = sessions;
        SelectionCards = cards;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getOrganiserId() {
        return OrganiserId;
    }

    public void setOrganiserId(int organiserId) {
        OrganiserId = organiserId;
    }

    public int getThemaId() {
        return ThemeId;
    }

    public void setThemaId(int themaId) {
        ThemeId = themaId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getThemeId() {
        return ThemeId;
    }

    public void setThemeId(int themeId) {
        ThemeId = themeId;
    }

    public ArrayList<Session> getSessions() {
        return Sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        Sessions = sessions;
    }

    public ArrayList<Card> getSelectionCards() {
        return SelectionCards;
    }

    public void setSelectionCards(ArrayList<Card> selectionCards) {
        SelectionCards = selectionCards;
    }
}

