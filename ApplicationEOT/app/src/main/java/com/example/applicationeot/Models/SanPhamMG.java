package com.example.applicationeot.Models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class SanPhamMG implements Serializable {
    private int idsp;
    private int iddm;
    private Bitmap imghinhsp;
    private String tensp;
    private String giasp;
    private int khuyenmai;
    private String ulrimage;
    private String noidung;
    private String nhacungcap;

    public SanPhamMG() {
    }

    public SanPhamMG(int idsp, int iddm, Bitmap imghinhsp, String tensp, String giasp, int khuyenmai, String ulrimage, String noidung, String nhacungcap) {
        this.idsp = idsp;
        this.iddm = iddm;
        this.imghinhsp = imghinhsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.khuyenmai = khuyenmai;
        this.ulrimage = ulrimage;
        this.noidung = noidung;
        this.nhacungcap = nhacungcap;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public int getIddm() {
        return iddm;
    }

    public void setIddm(int iddm) {
        this.iddm = iddm;
    }

    public Bitmap getImghinhsp() {
        return imghinhsp;
    }

    public void setImghinhsp(Bitmap imghinhsp) {
        this.imghinhsp = imghinhsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGiasp() {
        return giasp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    public int getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(int khuyenmai) {
        this.khuyenmai = khuyenmai;
    }

    public String getUlrimage() {
        return ulrimage;
    }

    public void setUlrimage(String ulrimage) {
        this.ulrimage = ulrimage;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNhacungcap() {
        return nhacungcap;
    }

    public void setNhacungcap(String nhacungcap) {
        this.nhacungcap = nhacungcap;
    }
}
