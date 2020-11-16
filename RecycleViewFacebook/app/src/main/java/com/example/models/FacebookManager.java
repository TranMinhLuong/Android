package com.example.models;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class FacebookManager implements Serializable {
    private int avatar;
    private String ten;
    private String minutes;
    private String status;
    private int hinhanh;

    public FacebookManager() {
    }

    public FacebookManager(int avatar, String ten, String minutes, String status, int hinhanh) {
        this.avatar = avatar;
        this.ten = ten;
        this.minutes = minutes;
        this.status = status;
        this.hinhanh = hinhanh;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }
}
