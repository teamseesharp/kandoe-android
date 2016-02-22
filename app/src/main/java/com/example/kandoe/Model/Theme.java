package com.example.kandoe.Model;

/**
 * Created by Michelle on 22-2-2016.
 */
public class Theme {
    private String name;
    private String Description;
    private String tag;

    public Theme(String name, String description, String tag) {
        this.name = name;
        Description = description;
        this.tag = tag;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
