package com.example.kandoe.Model;

import android.media.Image;

/**
 * Created by Michelle on 10-2-2016.
 */
public class User {
    private int Id;
    private String Email;
    private String FirstName;
    private String LastName;
    private Image Avatar;
    private boolean HasRegistered;

    public User(String firstName, String lastName, int id, String email, boolean hasRegistered, Image avatar) {
        FirstName = firstName;
        LastName = lastName;
        Id = id;
        Email = email;
        HasRegistered = hasRegistered;
        Avatar = avatar;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Image getAvatar() {
        return Avatar;
    }

    public void setAvatar(Image avatar) {
        Avatar = avatar;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public boolean isHasRegistered() {
        return HasRegistered;
    }

    public void setHasRegistered(boolean hasRegistered) {
        HasRegistered = hasRegistered;
    }
}
