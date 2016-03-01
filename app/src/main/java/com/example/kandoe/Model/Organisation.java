package com.example.kandoe.Model;

import java.util.ArrayList;

/**
 * Created by Michelle on 22-2-2016.
 */
public class Organisation {
    private String Name;
    private ArrayList<Session> sessions;
    private String Id;


    public Organisation(String name, ArrayList<Session> sessions, String id) {
        Name = name;
        this.sessions = sessions;
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
