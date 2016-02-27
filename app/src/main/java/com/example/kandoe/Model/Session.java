package com.example.kandoe.Model;

import android.media.Image;

import java.util.ArrayList;


/**
 * Created by Michelle on 20-2-2016.
 */
public class Session extends ArrayList<Session> {
    private int id;
    private Image snapshot;
    private int date;
    private int numberOfSteps;
    //CHAT??


    public Session(Image snapshot, int date, int numberOfSteps,int id) {
        this.snapshot = snapshot;
        this.date = date;
        this.numberOfSteps = numberOfSteps;
        this.id = id;
    }

    public Image getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Image snapshot) {
        this.snapshot = snapshot;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }
}
