package com.epicodus.chattle.models;

/**
 * Created by Guest on 7/14/16.
 */
public class User {
    private String fullName;
    private String email;
    private String password;
    private String uid;

    public User(String name, String email, String pass, String uid) {
        this.fullName = name;
        this.email = email;
        this.password = pass;
        this.uid = uid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFullName() {

        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUid() {
        return uid;
    }
}
