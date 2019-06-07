package com.example.gym_platform;

import java.util.Date;

public class Reservation {
    private String content;
    private String gymName;
    private String id;
    private String type;
    private String userUID;
    private Date start;
    private Date end;
    private String price;

    public Reservation() {
    }

    public Reservation(String content, String gymName, String id,String type,String userUID,Date start,Date end,String price) {
       this.content = content;
       this.gymName=gymName;
       this.id=id;
       this.type=type;
       this.userUID=userUID;
       this.start=start;
       this.end=end;
       this.price=price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
