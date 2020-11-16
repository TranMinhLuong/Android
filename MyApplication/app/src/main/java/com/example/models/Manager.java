package com.example.models;

import androidx.annotation.NonNull;

public class Manager {
    private String tennv;
    private String thucongtac;
    private int songayct;

    public Manager() {
    }

    public Manager(String tennv, String thucongtac, int songayct) {
        this.tennv = tennv;
        this.thucongtac = thucongtac;
        this.songayct = songayct;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getThucongtac() {
        return thucongtac;
    }

    public void setThucongtac(String thucongtac) {
        this.thucongtac = thucongtac;
    }

    public int getSongayct() {
        return songayct;
    }

    public void setSongayct(int songayct) {
        this.songayct = songayct;
    }

    @NonNull
    @Override
    public String toString() {
        return this.tennv+"\n Bắt đầu đi công tác từ thứ: "+this.thucongtac+"\n Số ngày muốn đi: "+this.songayct;
    }
}
