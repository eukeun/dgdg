package com.example.gym_platform;


public class reviewItem {
    public int drawableId;
    public String username;
    public String reviewtext;
    public String date;

    public reviewItem(int drawableId, String username, String reviewtext, String date) {
        this.drawableId = drawableId;
        this.username = username;
        this.reviewtext = reviewtext;
        this.date = date;
    }
}