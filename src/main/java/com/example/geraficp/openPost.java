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
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class openPost implements Initializable {
    @FXML
    private static Post post;
    private static User user;

    @FXML
    private Label username;
    @FXML
    private Label caption;
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

    public User getUser() {
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

        setPost(showPost.getPost());
        System.out.println(post);
        try {
            setUser(DataBaseConnection.findUserById(post.getSender_Id()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(user.getUSERNAME());
            run();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean isIn(User user, List<User> likes) {
        for (User user1 : likes) {
            if (user1.equals(user))
                return true;
        }
        return false;
    }

    private void run() throws SQLException, IOException {
        username.setText(user.getUSERNAME());
        Image image = new Image(getClass().getResourceAsStream(DataBaseConnection.getPic(post)));//adress aks porof yaro
        postimage.setImage(image);
        user_picture.setImage(new Image(getClass().getResourceAsStream(DataBaseConnection.getProfile(user))));
        Image image1 = new Image(getClass().getResourceAsStream("whitelike.png"));
        Image image2 = new Image(getClass().getResourceAsStream("redlike.png"));
        if (!getPost().getText().equals(null)) {
            caption.setText(post.getText());
        }
        like.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                List<User> likes = null;
                try {
                    likes = DataBaseConnection.findLikes(post);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                boolean isLiked = isIn(user, likes);
                if (isLiked) {
                    post.getLikes().remove(user);
                    like.setImage(image1);
                } else {
                    post.getLikes().add(user);
                    like.setImage(image2);
                }
                java.util.Date javaDate = new java.util.Date();
                java.util.Date mySQLDate = new java.sql.Date(javaDate.getTime());

                try {
                    DataBaseConnection.like(post, post.getSender_Id(), (java.sql.Date) mySQLDate);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        List<User> likes = DataBaseConnection.findLikes(post);
        boolean isLiked = isIn(user, likes);
        if (isLiked) {
            like.setImage(image2);
        } else {
            like.setImage(image1);
        }
        System.out.println("salam");
        TreeItem<String> rootItemmain = new TreeItem<>("more INFO");
        TreeItem<String> rootItemc = new TreeItem<>("comments");
        TreeItem<String> rootIteml = new TreeItem<>("likes");

        add.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!Objects.isNull(comment.getText())) {
                    java.util.Date javaDate = new java.util.Date();
                    java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
                    try {
                        DataBaseConnection.comment(getPost(), getUser(), comment.getText(), mySQLDate);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    TreeItem<String> rootItem2 = new TreeItem<>(comment.getText());
                    rootItemc.getChildren().add(rootItem2);
                }
            }
        });
        if (!DataBaseConnection.findAllComment(post).equals(null)) {
            for (Comment comment : DataBaseConnection.findAllComment(post)) {
                TreeItem<String> rootItem0 = new TreeItem<>(comment.getTextComment());
                if (!DataBaseConnection.findAllComment(comment).equals(null)) {
                    for (Comment comment1 : DataBaseConnection.findAllComment(comment)) {
                        TreeItem<String> rootItem1 = new TreeItem<>(comment1.getTextComment());
                        rootItem0.getChildren().add(rootItem1);
                    }
                }
                rootItemc.getChildren().add(rootItem0);
            }
        }

        if (!DataBaseConnection.findLikes(post).equals(null)) {
            for (User user2 :DataBaseConnection.findLikes(post)) {
                TreeItem<String> rootItem0 = new TreeItem<>(user2.getUSERNAME());
                rootIteml.getChildren().add(rootItem0);
            }
        }
        rootItemmain.getChildren().addAll(rootItemc,rootIteml);
        treeView.setRoot(rootItemmain);
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
