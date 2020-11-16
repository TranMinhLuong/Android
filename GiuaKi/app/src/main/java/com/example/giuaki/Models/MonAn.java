package com.example.giuaki.Models;

import java.io.Serializable;

public class MonAn implements Serializable {
    private int imghinh;
    private String tenmon;

    public MonAn() {
    }

    public MonAn(int imghinh, String tenmon) {
        this.imghinh = imghinh;
        this.tenmon = tenmon;
    }

    public int getImghinh() {
        return imghinh;
    }

    public void setImghinh(int imghinh) {
        this.imghinh = imghinh;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }
}
