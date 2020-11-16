package com.example.models;

public class DoVat {
    private  int id;
    private String ten;
    private String mota;
    private byte[] hinhanh;

    public DoVat() {
    }

    public DoVat(int id, String ten, String mota, byte[] hinhanh) {
        this.id = id;
        this.ten = ten;
        this.mota = mota;
        this.hinhanh = hinhanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }
}
