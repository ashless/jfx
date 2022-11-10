/*package com.example.geraficp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ChatMenu extends Menu {

    private final User user;
    private final GroupChat PVchat;
    private final User chosenUser;

    public ChatMenu(User user, GroupChat PVChat, User chosenUser) {
        super(new String[]{"single pm", "reply", "forward", "Back"});
        this.user = user;
        this.PVchat = PVChat;
        this.chosenUser = chosenUser;
    }

    public void runMenu() throws SQLException {

        while (true) {
            print();
            Chat chat = new Chat();
            ArrayList<Chat> chatList = new ArrayList<>();
            PVchat.setGROUP_ID(DataBaseConnection.chatExist(PVchat));
            switch (chooseOperation()) {
                case 1:
                    chat.setTEXT(new Input(
                            "Enter your pm:").getInputTextString());
                    chat.setSENDER_ID(user.getUSER_ID());

                    java.util.Date javaDate = new java.util.Date();
                    java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
                    chat.setDate(mySQLDate);

                    chatList.add(chat);
                    PVchat.setChats(chatList);

                    DataBaseConnection.addGroup(PVchat);
                    break;
                case 2:

                    Chat chosenPm = Choosepm();
                    if (!Objects.isNull(chosenPm)) {
                        chat.setTEXT("replied on:"
                                + Choosepm().getTEXT() + "-->>"
                                + new Input("Enter your reply Text:").getInputTextString());
                        chat.setSENDER_ID(user.getUSER_ID());

                        java.util.Date javaDate1 = new java.util.Date();
                        java.sql.Date mySQLDate1 = new java.sql.Date(javaDate1.getTime());
                        chat.setDate(mySQLDate1);

                        chatList.add(chat);
                        PVchat.setChats(chatList);
                        DataBaseConnection.addGroup(PVchat);
                    }
                    break;
                case 3:

                    Chat chosenPm1 = ChooseForwardpm();

                    if (!Objects.isNull(chosenPm1)) {
                        chat.setTEXT("forwarded!:  "
                                + Choosepm().getTEXT());
                        chat.setSENDER_ID(user.getUSER_ID());

                        User toUser = toUse();
                        java.util.Date javaDate1 = new java.util.Date();
                        java.sql.Date mySQLDate1 = new java.sql.Date(javaDate1.getTime());
                        chat.setDate(mySQLDate1);
                        ArrayList<User> ss = new ArrayList<>();
                        ss.add(user);
                        ss.add(toUser);
                        PVchat.setUsers(ss);
                        chatList.add(chat);
                        PVchat.setChats(chatList);
                        DataBaseConnection.addGroup(PVchat);
                    }
                    break;

                case 4:

                    return;
            }
        }

    }

    private Chat ChooseForwardpm() throws SQLException {
        ArrayList<Chat> chatsList = DataBaseConnection.findChatOfGroup(user);

        ArrayList<String> texts = DataBaseConnection.findChatOfGroups(user);

        texts.add("Back");
        String[] textchat = texts.toArray(new String[0]);
        System.out.println("Choose pm to forward :");
        return new ShowUsersInformation<Chat>(textchat, chatsList, true).runMenu();


    }


    public Chat Choosepm() throws SQLException {

        ArrayList<Chat> chatsList = DataBaseConnection.findChatOfGroup(PVchat);

        ArrayList<String> texts = DataBaseConnection.findChatOfGroups(PVchat);

        texts.add("Back");
        String[] textchat = texts.toArray(new String[0]);
        System.out.println("Choose pm to reply :");
        return new ShowUsersInformation<Chat>(textchat, chatsList, true).runMenu();

    }
    public User toUse() throws SQLException {

        ArrayList<User> UserList = new ArrayList<User>(DataBaseConnection.findAllUser());

        ArrayList<String> texts = new ArrayList<String>(DataBaseConnection.findAllUsers());

        texts.add("Back");
        String[] textfollow = texts.toArray(new String[0]);
        System.out.println("Choose a user :");
        return new ShowUsersInformation<User>(textfollow, UserList, true).runMenu();

    }
}



 */