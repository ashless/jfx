package com.example.geraficp;

import java.util.Date;

public class Chat {

    private Integer SENDER_ID;
    private String TEXT;
    private Integer ID;
    Date date;

    public Chat(Integer SENDER_ID, String TEXT, Integer ID) {
        this.SENDER_ID = SENDER_ID;
        this.TEXT = TEXT;
        this.ID = ID;
    }
    public Chat(){

    }

    public Integer getSENDER_ID() {
        return SENDER_ID;
    }

    public void setSENDER_ID(Integer SENDER_ID) {
        this.SENDER_ID = SENDER_ID;
    }

    public String getTEXT() {
        return TEXT;
    }

    public void setTEXT(String TEXT) {
        this.TEXT = TEXT;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
