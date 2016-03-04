package com.example.kandoe.Model;

/**
 * Created by Michelle on 22-2-2016.
 */
public class SubTheme {
    private int Id,OrganiserId,ThemaId;
    private String Name;

    public SubTheme() {
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
        return ThemaId;
    }

    public void setThemaId(int themaId) {
        ThemaId = themaId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

