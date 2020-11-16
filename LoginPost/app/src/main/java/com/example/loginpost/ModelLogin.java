package com.example.loginpost;

import java.io.Serializable;

public class ModelLogin implements Serializable {
    private int id;
    private String user;
    private String pass;
    private String ten;

    public ModelLogin() {
    }

    public ModelLogin(int id, String user, String pass, String ten) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
