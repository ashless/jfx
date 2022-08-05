package com.example.geraficp;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class openPost implements Initializable {
    @FXML
    private  Post post;
    private  User user;

    @FXML
    private Label username;
    @FXML
    private ImageView postimage;

    @FXML
    private ImageView like;

    @FXML
    private TextField comment;

    @FXML
    private ImageView user_picture;

    @FXML
    private Button add;
    @FXML
    private TreeView<String> treeView;
    @FXML
    private Button back;

    private Stage stage;
    private Scene scene;


    public void selectItem() {

        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();

        if (item != null) {
            System.out.println(item.getValue());
        }
    }


    public Post getPost() {
        return post;
    }

    public  User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUser(loginMenu.getUser());
        try {
            run();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void run() throws SQLException, IOException {
        username.setText(user.getUSERNAME());
        Image image = new Image(getClass().getResourceAsStream("1.jpg"));//adress aks porof yaro
        postimage.setImage(image);
        Image image1 = new Image(getClass().getResourceAsStream("whitelike.png"));
        like.setImage(image1);
        TreeItem<String> rootItem = new TreeItem<>("comments");
        add.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!Objects.isNull(comment.getText())) {
                    java.util.Date javaDate = new java.util.Date();
                    java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
                    try {
                        DataBaseConnection.comment(post, user, comment.getText(), mySQLDate);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    TreeItem<String> rootItem2 = new TreeItem<>(comment.getText());
                    rootItem.getChildren().add(rootItem2);
                }
            }
        });
        if (!DataBaseConnection.findAllComment(post).equals(null)) {
            for (Comment comment : DataBaseConnection.findAllComment(post)) {
                TreeItem<String> rootItem0 = new TreeItem<>(comment.getTextComment());
              /*  if (!DataBaseConnection.findAllCommentofcomment(comment).equals(null)) {
                    for (Comment comment1 : DataBaseConnection.findAllCommentofcomment(comment)) {
                        TreeItem<String> rootItem1 = new TreeItem<>(comment1.getTextComment());
                        rootItem0.getChildren().add(rootItem1);
                    }
                }  */
                rootItem.getChildren().add(rootItem0);
            }
        }
        treeView.setRoot(rootItem);

        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    switchToprofileMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void switchToprofileMenu() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("ProfileMenu.fxml"));
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.setUser(loginMenu.getUser());
        this.stage = HelloApplication.getInstance().getStage();
        scene = new Scene(root);

        stage.setTitle("ProfileMenu");
        stage.setScene(scene);
        stage.show();

    }
}
