package com.example.geraficp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private Stage stage;
    private static HelloApplication obj;
    // private constructor to force use of

    // getInstance() to create Singleton object
    public static HelloApplication getInstance() {
        if (obj == null) {
            obj = new HelloApplication();
        }
        return obj;
    }


    @Override
    public void start(Stage stage) throws IOException {

        obj = this;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}