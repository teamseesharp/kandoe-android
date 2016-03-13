package com.example.kandoe.Model;

import java.io.Serializable;
import java.util.ArrayList;


public class Theme implements Serializable{
    private int Id, OrganisationId,OrganiserId;
    private String Name,Description,Tags;

    private ArrayList<SubTheme> Subthemes;

    public Theme() {
    }

    public Theme(int id, int organisationId, int organiserId, String name, String description, String tags, ArrayList<SubTheme> subthemes) {
        Id = id;
        OrganisationId = organisationId;
        OrganiserId = organiserId;
        Name = name;
        Description = description;
        Tags = tags;
        Subthemes = subthemes;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getOrganisationId() {
        return OrganisationId;
    }

    public void setOrganisationId(int organisationId) {
        OrganisationId = organisationId;
    }

    public int getOrganiserId() {
        return OrganiserId;
    }

    public void setOrganiserId(int organiserId) {
        OrganiserId = organiserId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public ArrayList<SubTheme> getSubthemes() {
        return Subthemes;
    }

    public void setSubthemes(ArrayList<SubTheme> subthemes) {
        Subthemes = subthemes;
    }


}
