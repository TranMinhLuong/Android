package com.example.Models;

import java.io.Serializable;

public class CommentSanPham implements Serializable {
    private String idbl;
    private String idsp;
    private String tenkh;
    private String comment;
    private String rating;
    private String created;

    public CommentSanPham() {
    }

    public CommentSanPham(String idbl, String idsp, String tenkh, String comment, String rating, String created) {
        this.idbl = idbl;
        this.idsp = idsp;
        this.tenkh = tenkh;
        this.comment = comment;
        this.rating = rating;
        this.created = created;
    }

    public String getIdbl() {
        return idbl;
    }

    public void setIdbl(String idbl) {
        this.idbl = idbl;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
