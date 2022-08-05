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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProfileMenu implements Initializable {
    private static User user;
    private Stage stage;
    private Scene scene;

    public ProfileMenu(User user) {
        this.user = user;
        //  System.out.println("Welcome to your work bench... \n"
        //        + user.getFIRST_NAME() + "  "
        //      + user.getLAST_NAME());
    }

    public ProfileMenu() {

    }


    private final String[] menuList = {"post", "Comment", "like", "Show Tweet Of All Users", "follow", "private chat", "group chat", "Log out"};


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
                            switchToFollow();
                            //new FollowMenu(user).runMenu();
                            break;
                        case "private chat":

                            switchToChat();
                            // new privateChatMenu(user).runMenu();
                            break;
                        case "group chat":

                            //    new publicChatMenu(user).runMenu();
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

    public void setMenu_List(ListView<String> menu_List) {
        this.menu_List = menu_List;
    }


    private void switchToFollow() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FollowMenu.fxml"));
        new FollowMenu(user);
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        myLabel.setText("Follow");
        stage.setTitle("Follow");
        stage.setScene(scene);
        stage.show();
    }
    private void switchToChat() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("chat.fxml"));
        new FollowMenu(user);
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        myLabel.setText("Follow");
        stage.setTitle("Follow");
        stage.setScene(scene);
        stage.show();
    }

    public static void setUser(User user1) {
        user = user1;
    }

    public static User getUser() {
        return user;
    }
}



