package com.example.geraficp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class privateChatMenu implements Initializable {
    private User user;
    private Scene scene;
    private Stage stage;
    @FXML
    public CheckBox ch = new CheckBox();
    public static Boolean sss = false;


    public privateChatMenu(){

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] s = {"chat", "back"};
        setUser(ProfileMenu.getUser());
        System.out.println(user.getUSERNAME());

        menu_List.getItems().addAll(s);
        menu_List.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

                selectedItem1 = menu_List.getSelectionModel().getSelectedItem();
                try {
                    switch (selectedItem1) {

                        case "chat":
                            System.out.println(ch.isSelected());
                            if(ch.isSelected()){
                                sss = true;
                            }
                            Parent root = FXMLLoader.load(getClass().getResource("g.fxml"));
                            if (loginMenu.c)
                                root.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
                            new FollowMenu(user);
                            stage = HelloApplication.getInstance().getStage();
                            scene = new Scene(root);
                            myLabel.setText("pv");
                            stage.setTitle("pv");
                            stage.setScene(scene);
                            stage.show();
                            break;
                        case "back":
                            root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
                            new FollowMenu(user);
                            stage = HelloApplication.getInstance().getStage();
                            scene = new Scene(root);
                            myLabel.setText("Follow");
                            stage.setTitle("Follow");
                            stage.setScene(scene);
                            stage.show();
                            break;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void setMenu_List(ListView<String> menu_List) {
        this.menu_List = menu_List;
    }


}


