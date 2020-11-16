package com.example.model;

import java.io.Serializable;

public class Contact implements Serializable,Comparable<Contact> {
    private int ma;
    private String name;
    private String phone;

    public Contact() {
    }

    public Contact(int ma, String name, String phone) {
        this.ma = ma;
        this.name = name;
        this.phone = phone;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int compareTo(Contact o) {
       return this.name.compareTo(o.name);
    }
}

/*public class Contact implements Comparable<Contact> {
    private int ma;
    private String name;
    private String phone;

    public Contact(int ma, String name, String phone) {
        this.ma = ma;
        this.name = name;
        this.phone = phone;
    }


    public int getMa() {
        return ma;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public int compareTo(Contact o) {
        return this.name.compareTo(o.name);
    }
}*/
