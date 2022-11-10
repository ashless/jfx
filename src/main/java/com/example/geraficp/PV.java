package com.example.geraficp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.*;

@Setter
@Getter
public class PV implements Initializable {
    String selectedItem1;
    private User user;
    private User user2;
    private Scene scene;
    private Stage stage;
    private Pane root;
    private GroupChat gg;
    Timeline timeline = new Timeline();
    CheckBox c = new CheckBox();
    ArrayList<Integer> counter = new ArrayList<>();
    @FXML
    private TextField gname = new TextField();
    @FXML
    private TextField finda = new TextField();
    private final Button add = new Button("send");
    private Button addm = new Button("add");
    private TextField findm = new TextField();
    private final VBox chatBox = new VBox(5);

    private final TextField ch = new TextField();
    private List<Label> messages = new ArrayList<>();
    private ScrollPane container = new ScrollPane();
    private int index = 0;
    @FXML
    private ListView<String> menu_List2 = new ListView<>();
    private ListView<String> menu_List3 = new ListView<>();

    ArrayList<Chat> chatList = new ArrayList<>();

    public PV(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    GroupChat gp = new GroupChat();

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUser(ProfileMenu.getUser());
        ArrayList<String> ff = new ArrayList<>();
        ArrayList<User> fff = new ArrayList<>();
        ArrayList<String> ss = new ArrayList<>();
        ArrayList<GroupChat> sss = new ArrayList<>();

        for (int i = 0; i < DataBaseConnection.findfollowersOfUserU(user).size(); i++){
            ff.add(DataBaseConnection.findfollowersOfUserU(user).get(i).getUSERNAME());
            fff.add(DataBaseConnection.findfollowersOfUserU(user).get(i));
        }

        for (int i = 0; i < DataBaseConnection.findfollowingsOfUserU(user).size(); i++){
            ff.add(DataBaseConnection.findfollowingsOfUserU(user).get(i).getUSERNAME());
            fff.add(DataBaseConnection.findfollowingsOfUserU(user).get(i));
        }
        sss = DataBaseConnection.groupsOfUser(user);
        System.out.println(privateChatMenu.sss);
        if(privateChatMenu.sss) {
            for (int i = 0; i < DataBaseConnection.groupsOfUser(user).size(); i++) {
                ss.add(DataBaseConnection.groupsOfUser(user).get(i).getGROUP_NAME());
            }
        }
        String[] s = new String[ff.size() + ss.size()];
        ff.addAll(ss);
        s = ff.toArray(s);
        menu_List2.getItems().addAll(s);
        menu_List2.getItems().add("back");
        menu_List2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

                selectedItem1 = menu_List2.getSelectionModel().getSelectedItem();
                try {
                    switch (selectedItem1) {

                        case "back":
                            root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
                            new FollowMenu(user);
                            stage = HelloApplication.getInstance().getStage();
                            scene = new Scene(root);
                            stage.setTitle("chat");
                            stage.setScene(scene);
                            stage.show();
                            break;
                        default:
                            messages.clear();
                            chatBox.getChildren().clear();
                            int o = ff.size();
                            for(int i = 0; i < fff.size(); i++){
                                if(fff.get(i).getUSERNAME().equals(selectedItem1))
                                    o = i;
                            }
                            if(o < ff.size()) {
                                user2 = fff.get(o);
                                ArrayList<User> ss = new ArrayList<>();
                                ss.add(user);
                                ss.add(user2);
                                gp.setUsers(ss);
                                ArrayList<Chat> chats = DataBaseConnection.findChatOfGroup(gp);
                                for (Chat c : chats) {
                                    if (c.getSENDER_ID() == user.getUSER_ID()) {
                                        Label l = new Label(user.getUSERNAME() + ":" + c.getTEXT());
                                        l.setAlignment(Pos.CENTER_LEFT);
                                        messages.add(l);
                                        counter.add(1);
                                    } else {
                                        Label l = new Label(c.getTEXT() + ":" + user2.getUSERNAME());
                                        //l.setAlignment(Pos.CENTER_RIGHT);
                                        l.setAlignment(Pos.TOP_RIGHT);
                                        messages.add(l);
                                        counter.add(2);
                                    }
                                }
                                chatting();
                                startGame();
                            }
                            else {
                                    messages.clear();
                                    chatBox.getChildren().clear();
                                    initChatBox();
                                    gchat();

                            }
                            break;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        ArrayList<GroupChat> fs = new ArrayList<>();
        for(int i = 0; i < DataBaseConnection.groupsOfUser(user).size(); i++){

        }
        //String[] ss = new String[fs.size()];
        menu_List3.getItems().addAll(ss);
        menu_List3.getItems().add("back");
        menu_List3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                System.out.println(5555555);

                selectedItem1 = menu_List3.getSelectionModel().getSelectedItem();
                try {
                    switch (selectedItem1) {

                        case "back":
                            root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
                            new FollowMenu(user);
                            stage = HelloApplication.getInstance().getStage();
                            scene = new Scene(root);
                            stage.setTitle("chat");
                            stage.setScene(scene);
                            stage.show();
                            break;
                        default:

                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    private void initChatBox() throws SQLException{
        container.setPrefSize(216, 400);
        container.setContent(chatBox);
        chatBox.getStyleClass().add("chatbox");
    }

    @SneakyThrows
    private void chatting(){
        initChatBox();
        add.setOnAction(evt->{
            messages.add(new Label(user.getUSERNAME() + ":" + ch.getText()));
            counter.add(1);
            try {
                Chat chat = new Chat();
                chat.setSENDER_ID(user.getUSER_ID());
                chat.setTEXT(ch.getText());
                java.util.Date javaDate = new java.util.Date();
                java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
                chat.setDate(mySQLDate);
                chatList.clear();
                chatList.add(chat);
                gp.setChats(chatList);
                DataBaseConnection.addGroup(gp);
                startGame();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        startGame();
        ch.setAlignment(Pos.BOTTOM_CENTER);
        ch.setLayoutX(80);
        ch.setLayoutY(420);
        add.setLayoutX(250);
        add.setLayoutY(420);
        ImageView user_picture = new ImageView();
        stage = new Stage();
        if(!(DataBaseConnection.getProfile(user) == null)){
            user_picture.setImage(new Image(getClass().getResourceAsStream(DataBaseConnection.getProfile(user))));
            user_picture.setX(220);
            user_picture.setY(40);
            user_picture.setPreserveRatio(true);
            user_picture.setFitWidth(80);
            user_picture.setFitHeight(80);
        }
        else{
            user_picture.setImage(new Image(getClass().getResourceAsStream("3.jpg")));
            user_picture.setX(220);
            user_picture.setY(40);
            user_picture.setPreserveRatio(true);
            user_picture.setFitWidth(80);
            user_picture.setFitHeight(80);
        }
        add.setAlignment(Pos.BOTTOM_CENTER);
        root = new Pane();
        Text text = new Text(user2.getUSERNAME());
        text.setLayoutX(250);
        text.setLayoutY(20);
        root.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        root.getChildren().addAll(container, add, ch, text, user_picture);
        scene = new Scene(root,300,450);
        stage.setScene(scene);
        stage.show();
    }
    public void startGame() {
        timeline.setCycleCount(1);
        KeyFrame keyFrame = new KeyFrame (Duration.seconds (0.015), ae -> {
            try {
                for(int i = 0; i < messages.size(); i++){
                    Boolean s = true;
                    for(int t = 0; t < chatBox.getChildren().size(); t++){
                        if(chatBox.getChildren().get(t).equals(messages.get(i))){
                            s = false;
                        }
                    }

                    if(counter.get(i)%2 == 0) {
                        messages.get(i).setAlignment(Pos.CENTER_RIGHT);
                    }
                    else
                        messages.get(i).setAlignment(Pos.CENTER_LEFT);
                    if(i == messages.size() - 1){
                        messages.get(i).setAlignment(Pos.CENTER);
                    }

                    if(s){
                        System.out.println(messages.get(i).getAlignment());
                        chatBox.getChildren().add(messages.get(i));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        timeline.getKeyFrames().add (keyFrame);
        timeline.play ();

    }

    public void find(){
        User u = DataBaseConnection.findByUsername(finda.getText());
        if(!(u == null)){
            menu_List2.getItems().add(u.getUSERNAME());
        }
    }

    @SneakyThrows
    public void add(ActionEvent e){
        GroupChat GPChat = new GroupChat();
        ArrayList<User> accounts = new ArrayList<>();

        //accounts.add(chosenUser);
        accounts.add(user);
        GPChat.setUsers(accounts);
        GPChat.setGROUP_NAME(gname.getText());

        DataBaseConnection.addGroup2(GPChat);
    }
    @SneakyThrows
    public void gchat(){

        gg = DataBaseConnection.findGroup(selectedItem1);
        gg.setUsers(DataBaseConnection.findUsersOfGroup(gg));
        for(int i = 0; i < DataBaseConnection.findChatOfGroup(gg).size(); i++){
            messages.add(new Label(DataBaseConnection.findUserById(DataBaseConnection.findChatOfGroup(gg).
                    get(i).getSENDER_ID()).getUSERNAME() + ":" + DataBaseConnection.findChatOfGroup(gg).get(i).getTEXT()));
            counter.add(DataBaseConnection.findChatOfGroup(gg).get(i).getSENDER_ID());
        }
        startGame1();
        Text text = new Text(gg.getGROUP_NAME());
        text.setLayoutX(245);
        text.setLayoutY(20);
        Text text2 = new Text(Integer.toString(gg.getUsers().size()));
        text2.setLayoutX(250);
        text2.setLayoutY(10);
        ch.setAlignment(Pos.BOTTOM_CENTER);
        ImageView user_picture = new ImageView();
        user_picture.setImage(new Image(getClass().getResourceAsStream("1.jpg")));
        user_picture.setX(220);
        user_picture.setY(40);
        user_picture.setPreserveRatio(true);
        user_picture.setFitWidth(80);
        user_picture.setFitHeight(80);
        ch.setLayoutX(80);
        ch.setLayoutY(420);
        add.setLayoutX(250);
        add.setLayoutY(420);
        addm.setLayoutX(250);
        addm.setLayoutY(450);
        addm.setOnAction((e)->addMember());
        findm.setLayoutX(80);
        findm.setLayoutY(450);
        stage = new Stage();
        add.setAlignment(Pos.BOTTOM_CENTER);
        root = new Pane();
        root.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        root.getChildren().addAll(container, add, ch, addm, findm, user_picture, text, text2);
        scene = new Scene(root,300,500);
        stage.setScene(scene);
        stage.show();
        add.setOnAction(evt->{
            messages.add(new Label(user.getUSERNAME() + ":" + ch.getText()));
            counter.add(1);
            try {
                Chat chat = new Chat();
                chat.setSENDER_ID(user.getUSER_ID());
                chat.setTEXT(ch.getText());
                java.util.Date javaDate = new java.util.Date();
                java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
                chat.setDate(mySQLDate);
                chatList.clear();
                chatList.add(chat);
                gg.setChats(chatList);
                DataBaseConnection.addGroup(gg);
                startGame1();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    @SneakyThrows
    private void addMember(){
        String username = findm.getText();
        gg.setGROUP_NAME(selectedItem1);
        User us = DataBaseConnection.findByUsername(username);
        Boolean f = true;
        for(int i = 0; i < DataBaseConnection.findfollowersOfUserU(user).size(); i++){
            if(us.getUSERNAME().equals(DataBaseConnection.findfollowersOfUserU(user).get(i).getUSERNAME())){
                f = true;
            }
        }
        if(f) {
            DataBaseConnection.addGroup3(gg, us);
            gg.getUsers().add(us);
        }
    }
    public void startGame1() {
        timeline.setCycleCount(1);
        KeyFrame keyFrame = new KeyFrame (Duration.seconds (0.015), ae -> {
            try {
                for(int i = 0; i < messages.size(); i++){
                    Boolean s = true;
                    for(int t = 0; t < chatBox.getChildren().size(); t++){
                        if(chatBox.getChildren().get(t).equals(messages.get(i))){
                            s = false;
                        }
                    }
                    if(counter.get(i) == user.getUSER_ID()){
                        messages.get(i).setAlignment(Pos.CENTER_LEFT);
                    }
                    else{
                        messages.get(i).setAlignment(Pos.CENTER_RIGHT);

                    }
                    if(i == messages.size() - 1){
                        messages.get(i).setAlignment(Pos.CENTER);
                    }
                    if(s){
                        System.out.println(messages.get(i).getAlignment());
                        chatBox.getChildren().add(messages.get(i));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        timeline.getKeyFrames().add (keyFrame);
        timeline.play ();

    }

}
