package com.example.models;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private int imgava;
    private String ten;
    private String lop;
    private int msv;

    public SinhVien() {
    }

    public SinhVien(int imgava, String ten, String lop, int msv) {
        this.imgava = imgava;
        this.ten = ten;
        this.lop = lop;
        this.msv = msv;
    }

    public int getImgava() {
        return imgava;
    }

    public void setImgava(int imgava) {
        this.imgava = imgava;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public int getMsv() {
        return msv;
    }

    public void setMsv(int msv) {
        this.msv = msv;
    }
}
