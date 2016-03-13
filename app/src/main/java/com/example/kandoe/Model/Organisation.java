package com.example.kandoe.Model;

import java.util.ArrayList;

/**
 * Created by Michelle on 22-2-2016.
 */
public class Organisation {
    private int Id, OrganiserId;
    private String Name;

    private ArrayList<Session> Sessions;
    private ArrayList<Theme> Themes;

    public Organisation() {
    }

    public Organisation(int id, int organiserId, String name, ArrayList<Session> sessions, ArrayList<Theme> themes) {
        Id = id;
        OrganiserId = organiserId;
        Name = name;
        Sessions = sessions;
        Themes = themes;
    }

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
