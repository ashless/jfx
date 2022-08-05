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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    private Pane root = new Pane();
    Timeline timeline = new Timeline();
    CheckBox c = new CheckBox();

    @FXML
    private TextField gname = new TextField();
    @FXML
    private TextField find = new TextField();
    private final Button add = new Button("send");
    private final VBox chatBox = new VBox(5);

    private final TextField ch = new TextField("message...");
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

        for (int i = 0; i < DataBaseConnection.findfollowersOfUserU(user).size(); i++){
            ff.add(DataBaseConnection.findfollowersOfUserU(user).get(i).getUSERNAME());
            fff.add(DataBaseConnection.findfollowersOfUserU(user).get(i));
        }

        for (int i = 0; i < DataBaseConnection.findfollowingsOfUserU(user).size(); i++){
            ff.add(DataBaseConnection.findfollowingsOfUserU(user).get(i).getUSERNAME());
            fff.add(DataBaseConnection.findfollowingsOfUserU(user).get(i));
        }
        String[] s = new String[ff.size()];
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
                            int o = 0;
                            for(int i = 0; i < fff.size(); i++){
                                if(fff.get(i).getUSERNAME().equals(selectedItem1))
                                    o = i;
                            }
                            user2 = fff.get(o);
                            ArrayList<User> ss = new ArrayList<>();
                            ss.add(user);
                            ss.add(user2);
                            gp.setUsers(ss);
                            ArrayList<Chat> chats = DataBaseConnection.findChatOfGroup(gp);
                            for(Chat c : chats) {
                                messages.add(new Label(c.getTEXT()));
                            }
                            chatting();
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
        String[] ss = new String[fs.size()];
        menu_List3.getItems().addAll(ss);
        menu_List3.getItems().add("back");
        menu_List3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

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

        add.setOnAction(evt->{
            messages.add(new Label(ch.getText()));
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
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(index%2==0){
                messages.get(index).setAlignment(Pos.CENTER_LEFT);
                System.out.println("1");

            }else{

                messages.get(index).setAlignment(Pos.CENTER_RIGHT);
                System.out.println("2");

            }

            ;
            index++;

        });

        for(int i = 0; i < messages.size(); i++){
            Boolean s = true;
            for(int t = 0; t < chatBox.getChildren().size(); t++){
                if(chatBox.getChildren().get(t).equals(messages.get(i))){
                    s = false;
                }
            }
            if(s){
                chatBox.getChildren().add(messages.get(i));
            }
        }

    }

    @SneakyThrows
    private void chatting(){
        initChatBox();
        ch.setAlignment(Pos.BOTTOM_CENTER);
        ch.setLayoutX(80);
        ch.setLayoutY(420);
        add.setLayoutX(250);
        add.setLayoutY(420);
        stage = new Stage();
        add.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().addAll(container, add, ch);
        scene = new Scene(root,300,450);
        stage.setScene(scene);
        stage.show();
    }
    public void startGame() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame (Duration.seconds (0.015), ae -> {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        timeline.getKeyFrames().add (keyFrame);
        timeline.play ();

    }

    public void find(){

    }

    @SneakyThrows
    public void add(ActionEvent e){
        GroupChat GPChat = new GroupChat();
        ArrayList<User> accounts = new ArrayList<>();
        //accounts.add(chosenUser);
        accounts.add(user);
            GPChat.setUsers(accounts);
            GPChat.setGROUP_NAME(gname.getText());
            System.out.println(gname.getText());
            System.out.println(55);
        DataBaseConnection.addGroup2(GPChat);
    }
}
