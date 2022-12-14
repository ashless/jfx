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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class loginMenu {

    private static User user;
    private Stage stage;
    private Scene scene;
    public static Boolean c;
    @FXML
    CheckBox check = new CheckBox();

    @FXML
    private Label myLabel;
    @FXML
    private Label warning;
    @FXML
    private TextField username;
    @FXML
    private TextField password;


    public void loginClick(ActionEvent e) throws SQLException, IOException {
/*        System.out.println(username.getText());
        System.out.println(password.getText());*/
        //System.out.println(DataBaseConnection.findByUsername(username.getText(), password.getText()).getUSERNAME());
        User user1 = DataBaseConnection.findByUsername(username.getText(), password.getText());
        //System.out.println("1"+user1.getUSERNAME());
        setUser(user1);

        if (Objects.isNull(user)) {
            warning.setText("warning:Your password or username is wrong ...");
        } else {
            System.out.println("2"+user.getUSERNAME());
            switchToprofileMenu();

        }
    }


    public void switchToprofileMenu ()throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("ProfileMenu.fxml"));
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.setUser(getUser());
        this.stage = HelloApplication.getInstance().getStage();
        if(check.isSelected()) {
            root.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            c = true;
        }
        else {
            c = false;
        }
        scene = new Scene(root);
        myLabel.setText("ProfileMenu");
        stage.setTitle("ProfileMenu");
        stage.setScene(scene);
        stage.show();

    }

    @SneakyThrows
    public void forgot(ActionEvent e) throws IOException{
        User user1 = DataBaseConnection.findByUsernameAndKey(username.getText(), password.getText());
        //System.out.println("1"+user1.getUSERNAME());
        setUser(user1);
        if (Objects.isNull(user)) {
            warning.setText("warning:Your password or username is wrong ...");
        } else {
            System.out.println("2"+user.getUSERNAME());
            switchToprofileMenu();

        }
    }
    public static User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
