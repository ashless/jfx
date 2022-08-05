package com.example.geraficp;

import java.util.ArrayList;

public class GroupChat {

    private Integer GROUP_ID;
    private String GROUP_NAME;

    public GroupChat() {
    }

    //private
    private ArrayList<User> users;
    private ArrayList<Chat> chats;

    public GroupChat(Integer GROUP_ID, ArrayList<User> users, ArrayList<Chat> chats) {
        this.GROUP_ID = GROUP_ID;
        this.users = users;
        this.chats = chats;
    }



    public Integer getGROUP_ID() {
        return GROUP_ID;
    }

    public void setGROUP_ID(Integer GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public String getGROUP_NAME() {
        return GROUP_NAME;
    }

    public void setGROUP_NAME(String GROUP_NAME) {
        this.GROUP_NAME = GROUP_NAME;
    }
}
