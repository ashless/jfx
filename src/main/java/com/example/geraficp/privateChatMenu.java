package com.example.geraficp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class privateChatMenu extends Menu implements Initializable {
    private User user;

    public privateChatMenu(User user) {
        super(new String[]{"choose user", "Back"});
        this.user = user;
    }

    public privateChatMenu(){

    }

    private String[] menuList = {"post", "Comment", "like", "Show Tweet Of All Users", "follow", "private chat", "group chat", "Log out"};


    public String[] getMenuList() {
        return menuList;
    }

    @FXML
    private ListView<String> menu_List = new ListView<>();

    String selectedItem1;
    @FXML
    private Label myLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] s = {"post", "Comment", "like", "Show Tweet Of All Users", "follow", "private chat", "group chat", "Log out"};

        menu_List.getItems().addAll(s);
        menu_List.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

                selectedItem1 = menu_List.getSelectionModel().getSelectedItem();
                try {
                    switch (selectedItem1) {
                        case "post":


                            //  new PostMenu(user).runMenu();
                            break;
                        case "Comment":

                            // new CommentMenu(user).runMenu();
                            break;
                        case "like":

                            //new LikeMenu(user).runMenu();
                            break;
                        case "Show Tweet Of All Users":

                        /*System.out.println("***List all posts:***");
                        for (Post posts : DataBaseConnection.findAllPost()) {
                            System.out.print(posts.getUser().getUSERNAME() + "->");
                            System.out.println(posts.getText());
                        }

                         */
                            break;
                        case "follow":
                            //new FollowMenu(user).runMenu();
                            break;
                        case "private chat":


                            // new privateChatMenu(user).runMenu();
                            break;
                        case "group chat":

                            //    new publicChatMenu(user).runMenu();
                            break;
                        case "Log out":

                            return;


                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
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


