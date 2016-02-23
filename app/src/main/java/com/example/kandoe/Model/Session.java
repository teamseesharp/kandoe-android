package com.example.kandoe.Model;

import android.media.Image;

import org.joda.time.DateTime;

/**
 * Created by Michelle on 20-2-2016.
 */
public class Session {
    private Image snapshot;
    private DateTime date;
    //CHAT??


    public Session(Image snapshot, DateTime date) {
        this.snapshot = snapshot;
        this.date = date;
    }

    public Image getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Image snapshot) {
        this.snapshot = snapshot;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
