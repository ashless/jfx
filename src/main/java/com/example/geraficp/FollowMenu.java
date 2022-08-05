package com.example.geraficp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class FollowMenu  {

    private  static User user;
    private Stage stage;
    private Scene scene;

    public FollowMenu(User user) {
        FollowMenu.user = user;
        //  System.out.println("Welcome to your work bench... \n"
        //        + user.getFIRST_NAME() + "  "
        //      + user.getLAST_NAME());
    }

    public FollowMenu() {

    }

    @FXML
    private TextField choosenUser;

    @FXML
    private Label myLabel;

    @FXML
    private Label warning;

    private boolean isIn(User user, java.util.List<User> followers) {
        for (User user1 : followers) {
            if (user1.equals(user))
                return true;
        }
        return false;
    }

    public void loginClick(ActionEvent e) throws SQLException, IOException {


        User chosenUser = DataBaseConnection.findByUsername(choosenUser.getText());

        //System.out.println(tweet.getText());
        if (!Objects.isNull(chosenUser)) {
            chosenUser.setFollowers(DataBaseConnection.findfollowersOfUserU(chosenUser));
            for (User user : chosenUser.getFollowers()) {
                System.out.println(user.getFIRST_NAME());
            }
            ArrayList<User> followers = chosenUser.getFollowers();
            boolean isFollow = isIn(user, followers);
            if (isFollow) {
                chosenUser.getFollowers().remove(user);
                warning.setText("you followed this user befor / now we unfollow it");

            } else {

                chosenUser.getFollowers().add(user);
                warning.setText("now you follow this User");
            }

            DataBaseConnection.follow(user, chosenUser);
            switchToprofileMenu(e);

        }else {
            warning.setText("this user not found");
        }
    }


    public void switchToprofileMenu(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("ProfileMenu.fxml"));
        ProfileMenu profileMenu=new ProfileMenu();
        profileMenu.setUser(loginMenu.getUser());
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        myLabel.setText("ProfileMenu");
        stage.setTitle("ProfileMenu");
        stage.setScene(scene);
        stage.show();

    }

    public void setUser(User user) {
        FollowMenu.user =user;
    }
}


