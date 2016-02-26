package com.example.kandoe.Model;

/**
 * Created by Michelle on 22-2-2016.
 */
public class Card {
    private int id;
    private String text;
    private String description;
    private String URL;

    public Card(int id, String URL, String text, String description) {
        this.id = id;
        this.URL = URL;
        this.text = text;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


