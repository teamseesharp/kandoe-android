package com.example.kandoe.Model;

/**
 * Created by Michelle on 22-2-2016.
 */

public class Card {
    private int Id;
    private String Text;
    private String description;
    private String SubthemeId;
    private String Image;

    public Card() {
    }

    public Card(int id, String text, String subthemeId, String image) {
        Id = id;
        Text = text;
        SubthemeId = subthemeId;
        Image = image;
        this.description = "Geen beschrijving";
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
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubthemeId() {
        return SubthemeId;
    }

    public void setSubthemeId(String subthemeId) {
        SubthemeId = subthemeId;
    }
}


