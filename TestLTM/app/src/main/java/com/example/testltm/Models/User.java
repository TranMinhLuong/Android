package com.example.testltm.Models;

import java.io.Serializable;

public class User implements Serializable {
    private String userid;
    private String name;

    public User() {
    }

    public User(String userid) {
        this.userid = userid;
    }

    public User(String userid, String name) {
        this.userid = userid;
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
