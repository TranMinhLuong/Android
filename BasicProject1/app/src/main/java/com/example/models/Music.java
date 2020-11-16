package com.example.models;

public class Music {
    private String idsong;
    private String namesong;
    private String singger;
    private boolean like;

    public Music() {
    }

    public Music(String idsong, String namesong, String singger, boolean like) {
        this.idsong = idsong;
        this.namesong = namesong;
        this.singger = singger;
        this.like = like;
    }

    public String getIdsong() {
        return idsong;
    }

    public void setIdsong(String idsong) {
        this.idsong = idsong;
    }

    public String getNamesong() {
        return namesong;
    }

    public void setNamesong(String namesong) {
        this.namesong = namesong;
    }

    public String getSingger() {
        return singger;
    }

    public void setSingger(String singger) {
        this.singger = singger;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
