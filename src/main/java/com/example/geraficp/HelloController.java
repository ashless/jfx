package com.example.geraficp;

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
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private Stage stage;
    private Scene scene;


    @FXML
    private ListView<String> menuList1;
    String[] menuitem1 = {"Log Into Existing Account", "Create New Account", "Exit"};
    String selectedItem1;
    @FXML
    private Label myLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        menuList1.getItems().addAll(menuitem1);
        menuList1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

                selectedItem1 = menuList1.getSelectionModel().getSelectedItem();

                try {
                    switch (selectedItem1) {
                        case "Log Into Existing Account":
                            switchToLogin();
                            break;
                        case "Create New Account":
                            switchToCreate();
                            break;
                        case "Exit":
                            return;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void switchToCreate() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("createAccount.fxml"));
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        myLabel.setText(selectedItem1);
        stage.setTitle("createAccount");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        myLabel.setText(selectedItem1);
        stage.setTitle("login");
        stage.setScene(scene);
        stage.show();
    }

}
