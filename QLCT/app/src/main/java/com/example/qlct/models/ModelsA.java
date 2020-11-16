package com.example.qlct.models;

import java.io.Serializable;

public class ModelsA implements Serializable {
    private String ten;
    private String tuoi;

    public ModelsA() {
    }

    public ModelsA(String ten, String tuoi) {
        this.ten = ten;
        this.tuoi = tuoi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTuoi() {
        return tuoi;
    }

    public void setTuoi(String tuoi) {
        this.tuoi = tuoi;
    }
}
