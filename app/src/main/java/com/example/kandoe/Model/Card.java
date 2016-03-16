package com.example.kandoe.Model;

/**
 * Created by Michelle on 22-2-2016.
 */

public class Card {
    private int Id,SessionId,SessionLevel,SnapshotId,SubthemeId,ThemeId;
    private String Text,Image;

    public Card() {
    }

    public Card(int id, String text) {
        Id = id;
        this.Text = text;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }


    public int getSessionId() {
        return SessionId;
    }

    public void setSessionId(int sessionId) {
        SessionId = sessionId;
    }

    public int getSessionLevel() {
        return SessionLevel;
    }

    public void setSessionLevel(int sessionLevel) {
        SessionLevel = sessionLevel;
    }

    public int getSubthemeId() {
        return SubthemeId;
    }

    public void setSubthemeId(int subthemeId) {
        SubthemeId = subthemeId;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public int getThemeId() {
        return ThemeId;
    }

    public void setThemeId(int themeId) {
        ThemeId = themeId;
    }

    public int getSnapshotId() {
        return SnapshotId;
    }

    public void setSnapshotId(int snapshotId) {
        SnapshotId = snapshotId;
    }
}


