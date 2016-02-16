package com.example.kandoe.Model;

import android.media.Image;

/**
 * Created by Michelle on 10-2-2016.
 */
public class User {
    private String Email;
    private int Id;
    private Image Avatar;
    private boolean HasRegistered;

    public User(String email, int id, boolean hasRegistered,Image avatar) {
        Email = email;
        Id = id;
        HasRegistered = hasRegistered;
        Avatar = avatar;
    }

    public int getId() {
        return Id;
    }
}
