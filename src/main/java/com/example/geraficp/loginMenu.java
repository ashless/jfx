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
import java.util.Objects;
import java.util.ResourceBundle;

public class loginMenu {

    private static User user;
    private Stage stage;
    private Scene scene;

    @FXML
    private Label myLabel;
    @FXML
    private Label warning;
    @FXML
    private TextField username;
    @FXML
    private TextField password;


    public void loginClick(ActionEvent e) throws SQLException, IOException {

        User user = DataBaseConnection.findByUsername(username.getText(), password.getText());

        if (Objects.isNull(user)) {
           warning.setText("warning:Your password or username is wrong ...");
        }else{
            setUser(user);
            switchToprofileMenu(e);

        }
    }


    public void switchToprofileMenu(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("ProfileMenu.fxml"));
   ProfileMenu profileMenu=new ProfileMenu();
        profileMenu.setUser(getUser());
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        myLabel.setText("ProfileMenu");
        stage.setTitle("ProfileMenu");
        stage.setScene(scene);
        stage.show();

    }

    public static User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
