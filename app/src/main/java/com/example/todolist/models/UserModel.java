package com.example.todolist.models;

import java.io.Serializable;
import java.util.Date;

public class UserModel implements Serializable {
    private String username,password,fullname,email;
    private int gender;
    private Date birth;

    public UserModel() {
    }

    public UserModel(String username, String password, String fullname, String email, int gender, Date birth) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.gender = gender;
        this.birth = birth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
