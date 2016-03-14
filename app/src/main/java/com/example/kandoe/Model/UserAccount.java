package com.example.kandoe.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Michelle on 10-2-2016.
 */
public class UserAccount implements Serializable {
    private int Id;
    private String Email, Name,Picture, Surname,Secret;

    private ArrayList<ChatMessage> ChatMessages;
    private ArrayList<Organisation> Organisations;
    private ArrayList<Session> OrganisedSessions;
    private ArrayList<Session> ParticipatingSessions;
    private ArrayList<SubTheme> Subthemes;
    private ArrayList<Theme> Themes;



    public UserAccount(){

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getSecret() {
        return Secret;
    }

    public void setSecret(String secret) {
        Secret = secret;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public ArrayList<ChatMessage> getChatMessages() {
        return ChatMessages;
    }

    public void setChatMessages(ArrayList<ChatMessage> chatMessages) {
        ChatMessages = chatMessages;
    }

    public ArrayList<ChatMessage> getChatmessages() {
        return ChatMessages;
    }

    public void setChatmessages(ArrayList<ChatMessage> chatmessages) {
        ChatMessages = chatmessages;
    }

    public ArrayList<Organisation> getOrganisations() {
        return Organisations;
    }

    public void setOrganisations(ArrayList<Organisation> organisations) {
        Organisations = organisations;
    }

    public ArrayList<Session> getOrganisedSessions() {
        return OrganisedSessions;
    }

    public void setOrganisedSessions(ArrayList<Session> organisedSessions) {
        OrganisedSessions = organisedSessions;
    }

    public ArrayList<Session> getParticipatingSessions() {
        return ParticipatingSessions;
    }

    public void setParticipatingSessions(ArrayList<Session> participatingSessions) {
        ParticipatingSessions = participatingSessions;
    }

    public ArrayList<SubTheme> getSubthemes() {
        return Subthemes;
    }

    public void setSubthemes(ArrayList<SubTheme> subthemes) {
        Subthemes = subthemes;
    }

    public ArrayList<Theme> getThemes() {
        return Themes;
    }

    public void setThemes(ArrayList<Theme> themes) {
        Themes = themes;
    }
}
