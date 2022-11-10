package com.example.geraficp;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class gchat {

    String selectedItem1;
    private User user;
    private User user2;
    private Scene scene;
    private Stage stage;
    private Pane root = new Pane();
    Timeline timeline = new Timeline();

    private final Button add = new Button("send");
    private final VBox chatBox = new VBox(5);

    private final TextField ch = new TextField("message...");
    private List<Label> messages = new ArrayList<>();
    private ScrollPane container = new ScrollPane();
    private int index = 0;
    @FXML
    private ListView<String> menu_List2 = new ListView<>();
    ArrayList<Chat> chatList = new ArrayList<>();

    GroupChat gp = new GroupChat();

}
