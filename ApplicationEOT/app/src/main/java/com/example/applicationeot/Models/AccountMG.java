package com.example.applicationeot.Models;

import java.io.Serializable;

public class AccountMG implements Serializable {
    private int id;
    private String name;
    private String taikhoan;
    private String matkhau;

    public AccountMG() {
    }

    public AccountMG(int id, String name, String taikhoan, String matkhau) {
        this.id = id;
        this.name = name;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
