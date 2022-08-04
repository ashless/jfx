package com.example.geraficp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class FollowMenu implements Initializable {

    private  User user;
    private Stage stage;
    private Scene scene;

    public FollowMenu(User user) {
        this.user = user;
        //  System.out.println("Welcome to your work bench... \n"
        //        + user.getFIRST_NAME() + "  "
        //      + user.getLAST_NAME());
    }
    public FollowMenu() {

    }
    private String[] menuList = {"follow", "Show My followers", "Show MY followings","Back"};

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


        menu_List.getItems().addAll(menuList);
        menu_List.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

                selectedItem1 = menu_List.getSelectionModel().getSelectedItem();
                try {
                    switch (selectedItem1) {
                        case "follow":


                            //  new PostMenu(user).runMenu();
                            break;
                        case  "Show My followers":

                            // new CommentMenu(user).runMenu();
                            break;
                        case  "Show MY followings":

                            //new LikeMenu(user).runMenu();
                            break;
                        case "Back":

                            return;


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void setMenu_List(ListView<String> menu_List) {
        this.menu_List = menu_List;
    }

/*
    public void runMenu() throws SQLException {

        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    User chosenUser = follow();

                    //System.out.println(tweet.getText());
                    if (!Objects.isNull(chosenUser)){
                        chosenUser.setFollowers(DataBaseConnection.findfollowersOfUserU(chosenUser));
                        for(User user : chosenUser.getFollowers()){
                            System.out.println(user.getFIRST_NAME());
                        }
                        ArrayList<User> followers = chosenUser.getFollowers();
                        boolean isFollow = isIn(user, followers);
                        if (isFollow) {
                            chosenUser.getFollowers().remove(user);
                            System.out.println("you followed this user befor / now we unfollow it");

                        } else {

                            chosenUser.getFollowers().add(user);
                            System.out.println("now you follow this User");
                        }

                        DataBaseConnection.follow(user,chosenUser);
                    }
                    break;
                case 2:
                    for (String followers : DataBaseConnection.findfollowersOfUser(user)) {
                        System.out.println(followers);
                    }
                    break;
                case 3:
                    for (String followings : DataBaseConnection.findfollowingsOfUser(user)) {
                        System.out.println(followings);
                    }
                    break;
                case 4:
                    return;
            }
        }

    }
    private boolean isIn(User user, java.util.List<User> followers) {
        for (User user1 : followers) {
            if (user1.equals(user))
                return true;
        }
        return false;
    }

    public User follow() throws SQLException {

        ArrayList<User> UserList = new ArrayList<User>(DataBaseConnection.findAllUser());

        ArrayList<String> texts = new ArrayList<String>(DataBaseConnection.findAllUsers());

        texts.add("Back");
        String[] textfollow = texts.toArray(new String[0]);
        System.out.println("Enter your User to add in/remove from your followings :");
        return new ShowUsersInformation<User>(textfollow, UserList, true).runMenu();

    }

 */
}

