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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProfileMenu implements Initializable {
    private static User user;
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
    @FXML
    private Label username;
    @FXML
    private ImageView imageView;
    @FXML
    private Label followers;
    @FXML
    private Label followings;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] s = {"Add Post", "show my post", "Show Tweet Of All Users", "follow", "chat", "Log out"};
        run();
        menu_List.getItems().addAll(s);
        menu_List.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                System.out.println(user.getUSERNAME());
                selectedItem1 = menu_List.getSelectionModel().getSelectedItem();
                try {
                    switch (selectedItem1) {
                        case "Add Post":

                            switchToPostMenu();
                            //  new PostMenu(user).runMenu();
                            break;
                        case "show my post":
                            switchToshowPost();
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
                        case "chat":
                            switchToChat();
                            break;

                        case "Log out":
                            switchTomainpage();


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void switchTomainpage() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        stage.setTitle("main page");
        stage.setScene(scene);
        stage.show();
    }

    private void run() throws SQLException {
        setUser(loginMenu.getUser());
        username.setText(getUser().getUSERNAME());
        int followersn = 0, followingsn = 0;
        for (String s : DataBaseConnection.findfollowersOfUser(getUser())) {
            followersn++;
        }
        for (String s : DataBaseConnection.findfollowingsOfUser(getUser())) {
            followingsn++;
        }

        followers.setText("followers:" + Integer.toString(followersn));
        followings.setText("followings:" + Integer.toString(followingsn));
        if(!(DataBaseConnection.getProfile(user) == null))
            imageView.setImage(new Image(getClass().getResourceAsStream(DataBaseConnection.getProfile(user))));//apdatepictute
    }

    private void switchToshowPost() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("showPost.fxml"));
        if (loginMenu.c)
            root.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        showPost showPost = new showPost();
        showPost.setUser(loginMenu.getUser());
        System.out.println(user.getUSERNAME());
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        stage.setTitle("show Post");
        stage.setScene(scene);
        stage.show();
    }

    private void switchToPostMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PostMenu.fxml"));
        if (loginMenu.c)
            root.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
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
        if (loginMenu.c)
            root.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        FollowMenu followMenu = new FollowMenu();
        followMenu.setUser(loginMenu.getUser());
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        myLabel.setText("Follow");
        stage.setTitle("Follow");
        stage.setScene(scene);
        stage.show();
    }

    @SneakyThrows
    private void switchToChat() {
        Parent root = FXMLLoader.load(getClass().getResource("chat.fxml"));
        if (loginMenu.c)
            root.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        FollowMenu followMenu = new FollowMenu();
        followMenu.setUser(loginMenu.getUser());
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        myLabel.setText("chat");
        stage.setTitle("chat");
        stage.setScene(scene);
        stage.show();


    }

    public static User getUser() {
        return user;
    }
}



