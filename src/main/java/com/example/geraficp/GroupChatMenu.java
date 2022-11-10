/*package com.example.geraficp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class GroupChatMenu extends Menu {
    private final User user;
    private final GroupChat GPchat;
    private ArrayList<User> accounts = new ArrayList<>();


    public GroupChatMenu(User user, GroupChat GPChat) {
        super(new String[]{"add member", "single pm", "reply", "forward", "Back"});
        this.user = user;
        this.GPchat = GPChat;
    }

    public void runMenu() throws SQLException {

        while (true) {
            print();
            Chat chat = new Chat();
            ArrayList<Chat> chatList = new ArrayList<>();
            GPchat.setGROUP_ID(DataBaseConnection.chatExist(GPchat));
            switch (chooseOperation()) {
                case 1:
                    User chosenUser = ChooseUser();
                    //System.out.println(tweet.getText());
                    if (!Objects.isNull(chosenUser)) {
                        // ali helpppppppppppp
                        if (DataBaseConnection.memberExist(chosenUser, GPchat)) {
                            accounts.add(chosenUser);
                            GPchat.setUsers(accounts);
                            DataBaseConnection.addGroup2(GPchat);
                            System.out.println("Added!");

                        }
                    break;
                    }
                case 2:
                    chat.setTEXT(new Input(
                            "Enter your pm:").getInputTextString());
                    chat.setSENDER_ID(user.getUSER_ID());

                    java.util.Date javaDate = new java.util.Date();
                    java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
                    chat.setDate(mySQLDate);

                    chatList.add(chat);
                    GPchat.setChats(chatList);

                    DataBaseConnection.addGroup(GPchat);
                    break;
                case 3:

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
                        GPchat.setChats(chatList);
                        DataBaseConnection.addGroup(GPchat);
                    }
                    break;
                case 4:

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
                        GPchat.setUsers(ss);
                        chatList.add(chat);
                        GPchat.setChats(chatList);
                        DataBaseConnection.addGroup(GPchat);
                    }
                    break;

                case 5:

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

        ArrayList<Chat> chatsList = DataBaseConnection.findChatOfGroup(GPchat);

        ArrayList<String> texts = DataBaseConnection.findChatOfGroups(GPchat);

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

    public User ChooseUser() throws SQLException {

        ArrayList<User> UserList = new ArrayList<User>(DataBaseConnection.findAllUser());

        ArrayList<String> texts = new ArrayList<String>(DataBaseConnection.findAllUsers());

        texts.add("Back");
        String[] textfollow = texts.toArray(new String[0]);
        System.out.println("Enter User to add in group chat :");
        return new ShowUsersInformation<User>(textfollow, UserList, true).runMenu();

    }
}


 */