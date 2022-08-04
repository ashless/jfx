/*package com.example.geraficp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class privateChatMenu extends Menu {
    private final User user;

    public privateChatMenu(User user) {
        super(new String[]{"choose user", "Back"});
        this.user = user;
    }

    public void runMenu() throws SQLException {

        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    User chosenUser = ChooseUser();
                    GroupChat PVChat = new GroupChat();
                    ArrayList<User> accounts = new ArrayList<>();
                    accounts.add(user);
                    accounts.add(chosenUser);
                    //System.out.println(tweet.getText());
                    if (!Objects.isNull(chosenUser)) {
                        PVChat.setUsers(accounts);
                    }
                    new ChatMenu(user,PVChat, chosenUser).runMenu();
                    break;
                case 2:
                    return;
            }
        }

    }

    public User ChooseUser() throws SQLException {

        ArrayList<User> UserList = new ArrayList<User>(DataBaseConnection.findAllUser());

        ArrayList<String> texts = new ArrayList<String>(DataBaseConnection.findAllUsers());

        texts.add("Back");
        String[] textfollow = texts.toArray(new String[0]);
        System.out.println("Enter your User to start  private chat :");
        return new ShowUsersInformation<User>(textfollow, UserList, true).runMenu();

    }
}


 */