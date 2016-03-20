package com.example.kandoe.Model;

import java.util.ArrayList;

public class Organisation {
    private int Id, OrganiserId;
    private String Name;

    private ArrayList<Session> Sessions;
    private ArrayList<Theme> Themes;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Session> getSessions() {
        return Sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
       Sessions = sessions;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public ArrayList<Theme> getThemes() {
        return Themes;
    }

    public void setThemes(ArrayList<Theme> themes) {
        Themes = themes;
    }

    public int getOrganiserId() {
        return OrganiserId;
    }

    public void setOrganiserId(int organiserId) {
        OrganiserId = organiserId;
    }
}
