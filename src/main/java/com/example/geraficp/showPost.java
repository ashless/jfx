package com.example.geraficp;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class showPost implements Initializable {

    private static User user;
    private Post post;
    //ImageView is a Node used for painting images loaded with Images

    // Image = picture
    // ImageView = picture frame

    //    Button myButton;
    @FXML
    GridPane gridpane;
    private Stage stage;
    private Scene scene;


    EventHandler mouseHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setUser(loginMenu.getUser());
        select();

    }

    public void select() {
        int i = 0, j = 0, z = 1;
        try {
            for (Post post : DataBaseConnection.findPostsOfUser(user)) {
                Image image = new Image(getClass().getResourceAsStream("1.jpg"));//address aks update beshe
                ImageView imageView = new ImageView(image);
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(150);
                imageView.setFitHeight(100);
                setPost(post);
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        try {
                            switchToopenPost(getPost());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                gridpane.add(imageView, j, i);
                j++;
                z++;
                if (j > 3) {
                    j = 0;
                    i++;
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void switchToopenPost(Post post) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("openPost.fxml"));
        openPost openPost = new openPost();
        openPost.setPost(getPost());
        openPost.setUser(loginMenu.getUser());
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);
        stage.setTitle("post");
        stage.setScene(scene);
        stage.show();

    }


    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

