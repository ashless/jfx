package com.example.geraficp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class createAcountMenu {

    private Stage stage;
    private Scene scene;
    @FXML
    private TextField username = new TextField();
    @FXML
    private TextField password = new TextField();
    @FXML
    private TextField phone = new TextField();
    @FXML
    private TextField age = new TextField();
    @FXML
    private TextField email = new TextField();
    @FXML
    private TextField business = new TextField();
    @FXML
    private TextField name = new TextField();
    @FXML
    private TextField lastname = new TextField();
    @FXML
    private TextField answer = new TextField();

    @FXML
    private void submit(ActionEvent e) throws SQLException, IOException {

        DataBaseConnection.addUser(new User(name.getText(), lastname.getText(), phone.getText(), email.getText(),
                Integer.parseInt(age.getText()), business.getText(), username.getText(), password.getText(), answer.getText()));

        Parent root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        stage.setTitle("main page");
        stage.setScene(scene);
        stage.show();
    }

}
