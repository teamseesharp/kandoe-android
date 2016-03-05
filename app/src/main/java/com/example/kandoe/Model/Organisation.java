package com.example.kandoe.Model;

import java.util.ArrayList;

/**
 * Created by Michelle on 22-2-2016.
 */
public class Organisation {
    private String Name;
    private ArrayList<Session> Sessions;
    private String Id;
    private ArrayList<Theme> Themes;

    public Organisation() {
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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public ArrayList<Theme> getThemes() {
        return Themes;
    }

    public void setThemes(ArrayList<Theme> themes) {
        Themes = themes;
    }
}
