package com.example.geraficp;//show tweet of all users

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProfileMenu implements Initializable {
    private User user;
    private Stage stage;
    private Scene scene;

    //   public ProfileMenu(User user) {
    //     this.user = user;
    //  System.out.println("Welcome to your work bench... \n"
    //        + user.getFIRST_NAME() + "  "
    //      + user.getLAST_NAME());
    // }

    public ProfileMenu() {

    }


    private String[] menuList = {"post", "Comment", "like", "Show Tweet Of All Users", "follow", "private chat", "group chat", "Log out"};

    public void setUser(User user) {
        this.user = user;
    }


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
        String[] s = {"Add Post", "Comment", "like", "Show Tweet Of All Users", "follow", "private chat", "group chat", "show post", "Log out"};

        menu_List.getItems().addAll(s);
        menu_List.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

                selectedItem1 = menu_List.getSelectionModel().getSelectedItem();
                try {
                    switch (selectedItem1) {
                        case "Add Post":

                            switchToPostMenu();
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
                            switchToFollow();
                            //new FollowMenu(user).runMenu();
                            break;
                        case "private chat":

                            // new privateChatMenu(user).runMenu();
                            break;
                        case "group chat":

                            //    new publicChatMenu(user).runMenu();
                            break;
                        case "show post":
                            switchToshowPost();
                            break;
                        case "Log out":

                            return;


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void switchToshowPost() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("showPost.fxml"));
        showPost showPost = new showPost();
        showPost.setUser(loginMenu.getUser());
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        stage.setTitle("show Post");
        stage.setScene(scene);
        stage.show();
    }

    private void switchToPostMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PostMenu.fxml"));
        PostMenu postMenu = new PostMenu();
        postMenu.setUser(loginMenu.getUser());
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        myLabel.setText("Add Post");
        stage.setTitle("Add Post");
        stage.setScene(scene);
        stage.show();
    }

    public void setMenu_List(ListView<String> menu_List) {
        this.menu_List = menu_List;
    }


    private void switchToFollow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FollowMenu.fxml"));
        FollowMenu followMenu = new FollowMenu();
        followMenu.setUser(loginMenu.getUser());
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        myLabel.setText("Follow");
        stage.setTitle("Follow");
        stage.setScene(scene);
        stage.show();
    }

    public User getUser() {
        return user;
    }
}



