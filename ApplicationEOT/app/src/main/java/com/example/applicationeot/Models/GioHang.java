package com.example.applicationeot.Models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class GioHang implements Serializable {
    public int idsp;
    public String tenspgh;
    public String giaspgh;
    public String urlhinhspgh;
    public Bitmap imghinhspgh;
    public int soluongsp;
    public int kmgh;

    public GioHang() {
    }

    public GioHang(int idsp, String tenspgh, String giaspgh, String urlhinhspgh, Bitmap imghinhspgh, int soluongsp, int kmgh) {
        this.idsp = idsp;
        this.tenspgh = tenspgh;
        this.giaspgh = giaspgh;
        this.urlhinhspgh = urlhinhspgh;
        this.imghinhspgh = imghinhspgh;
        this.soluongsp = soluongsp;
        this.kmgh = kmgh;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTenspgh() {
        return tenspgh;
    }

    public void setTenspgh(String tenspgh) {
        this.tenspgh = tenspgh;
    }

    public String getGiaspgh() {
        return giaspgh;
    }

    public void setGiaspgh(String giaspgh) {
        this.giaspgh = giaspgh;
    }

    public String getUrlhinhspgh() {
        return urlhinhspgh;
    }

    public void setUrlhinhspgh(String urlhinhspgh) {
        this.urlhinhspgh = urlhinhspgh;
    }

    public Bitmap getImghinhspgh() {
        return imghinhspgh;
    }

    public void setImghinhspgh(Bitmap imghinhspgh) {
        this.imghinhspgh = imghinhspgh;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }

    public int getKmgh() {
        return kmgh;
    }

    public void setKmgh(int kmgh) {
        this.kmgh = kmgh;
    }
}
