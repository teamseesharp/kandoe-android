package com.example.kandoe.Model;

/**
 * Created by Michelle on 10-2-2016.
 */
public class RegisterUser {
    private String email;
    private String password;
    private String confirmpassword;
    private String lastname;
    private String firstname;

    public RegisterUser(){

    }

    public RegisterUser(String email, String password, String confirmpassword, String lastname, String firstname) {
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
