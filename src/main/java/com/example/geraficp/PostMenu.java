package com.example.geraficp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PostMenu implements Initializable {
    private static User user;
    private Stage stage;
    private Scene scene;


    public PostMenu() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUser(ProfileMenu.getUser());
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private TextField imageAddress;
    @FXML
    private TextField caption;

    @FXML
    private Label warning;
    @FXML
    private Label myLabel;


    @FXML
    private void addClick() throws SQLException, IOException {
        Post post = new Post();
        if (user.getBusiness().equals("B")) {
            post.setText("Ad: " + caption.getText());
        } else {
            post.setText(caption.getText());
        }
        post.setAddress(imageAddress.getText());

        java.util.Date javaDate = new java.util.Date();
        java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
        post.setCREATE_DATE_TIME(mySQLDate);
        post.setLAST_UPDATE_DATE_TIME(mySQLDate);
        System.out.println("to add :"+user);
        post.setUser(user);
        post.setPost_Id(DataBaseConnection.addPost(post));
        System.out.println("to add post:"+post);
        if (user.getBusiness().equals("B")) {
            warning.setText("your advertise post add to your account");
        } else {
            warning.setText("your post add to your account");
        }
        switchToprofileMenu();
    }


    public void switchToprofileMenu() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("ProfileMenu.fxml"));
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.setUser(loginMenu.getUser());
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        myLabel.setText("ProfileMenu");
        stage.setTitle("ProfileMenu");
        stage.setScene(scene);
        stage.show();

    }

}


