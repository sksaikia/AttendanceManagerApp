package com.example.attendancemanager.ui.main.home.adapter;

public class HomeItem {
    private Integer image;
    private String text;

    public Integer getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public HomeItem(Integer image, String text) {
        this.image = image;
        this.text = text;
    }
}
