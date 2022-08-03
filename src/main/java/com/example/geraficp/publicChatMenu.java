/*package com.example.geraficp;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class publicChatMenu extends Menu {
           private final User user;

           public publicChatMenu(User user) {
               super(new String[]{"make group", "Back"});
               this.user = user;
           }

           public void runMenu() throws SQLException {

               while (true) {
                   print();
                   switch (chooseOperation()) {
                       case 1:
                           String GroupName = new Input("Enter Group Name :").getInputString();
                           //User chosenUser = ChooseUser();
                           GroupChat GPChat = new GroupChat();
                           ArrayList<User> accounts = new ArrayList<>();
                           //accounts.add(chosenUser);
                           accounts.add(user);

                           if (!Objects.isNull(GroupName)) {
                               GPChat.setUsers(accounts);
                               GPChat.setGROUP_NAME(GroupName);
                           }
                           new GroupChatMenu(user,GPChat).runMenu();
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