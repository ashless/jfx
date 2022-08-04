package com.example.geraficp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class privateChatMenu extends Menu {
    private User user;

    public privateChatMenu(User user) {
        super(new String[]{"choose user", "Back"});
        this.user = user;
    }

    public privateChatMenu(){

    }

    public void runMenu() throws SQLException {


    }
/*
    public User ChooseUser() throws SQLException {

        ArrayList<User> UserList = new ArrayList<User>(DataBaseConnection.findAllUser());

        ArrayList<String> texts = new ArrayList<String>(DataBaseConnection.findAllUsers());

        texts.add("Back");
        String[] textfollow = texts.toArray(new String[0]);
        System.out.println("Enter your User to start  private chat :");
        return new ShowUsersInformation<User>(textfollow, UserList, true).runMenu();

    }*/
}


