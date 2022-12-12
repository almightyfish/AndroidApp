package com.example.myapplication.entity;

public class Info {
    private int id;
    private String name;
    private String img;
    private String address;

    public Info(int id, String name, String img,String address) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
