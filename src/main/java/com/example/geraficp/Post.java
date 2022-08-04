package com.example.geraficp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Post {

    private Date CREATE_DATE_TIME;
    private Date LAST_UPDATE_DATE_TIME;
    private Integer post_Id;
    private Integer sender_Id;


    private String text;
    private User user;
    private List<Comment> comments = new ArrayList<>();
    private List<User>likes = new ArrayList<>();


    public Post(Date CREATE_DATE_TIME, Date LAST_UPDATE_DATE_TIME, Integer post_Id, User user, String text) {
        this.CREATE_DATE_TIME = CREATE_DATE_TIME;
        this.LAST_UPDATE_DATE_TIME = LAST_UPDATE_DATE_TIME;
        this.post_Id = post_Id;
        this.text = text;
        this.user = user;
    }
    public Post(Date CREATE_DATE_TIME, Date LAST_UPDATE_DATE_TIME, Integer post_Id,
                List<Comment> comments, List<User> likes) {
        this.CREATE_DATE_TIME = CREATE_DATE_TIME;
        this.LAST_UPDATE_DATE_TIME = LAST_UPDATE_DATE_TIME;
        this.post_Id = post_Id;
        this.comments = comments;
        this.likes = likes;
    }

    public Post(Date CREATE_DATE_TIME, Date LAST_UPDATE_DATE_TIME, Integer post_Id, Integer sender_Id, String text) {
        this.CREATE_DATE_TIME = CREATE_DATE_TIME;
        this.LAST_UPDATE_DATE_TIME = LAST_UPDATE_DATE_TIME;
        this.post_Id = post_Id;
        this.text = text;
        this.sender_Id = sender_Id;
    }

    public Post() {
    }

    public Post(String text){
        this.text = text;
    }

    public Integer getSender_Id() {
        return sender_Id;
    }

    public void setSender_Id(Integer sender_Id) {
        this.sender_Id = sender_Id;
    }

    public Date getCREATE_DATE_TIME() {
        return CREATE_DATE_TIME;
    }

    public void setCREATE_DATE_TIME(Date CREATE_DATE_TIME) {
        this.CREATE_DATE_TIME = CREATE_DATE_TIME;
    }

    public Date getLAST_UPDATE_DATE_TIME() {
        return LAST_UPDATE_DATE_TIME;
    }

    public void setLAST_UPDATE_DATE_TIME(Date LAST_UPDATE_DATE_TIME) {
        this.LAST_UPDATE_DATE_TIME = LAST_UPDATE_DATE_TIME;
    }

    public Integer getPost_Id() {
        return post_Id;
    }

    public void setPost_Id(Integer post_Id) {
        this.post_Id = post_Id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Post{" +
                "CREATE_DATE_TIME=" + CREATE_DATE_TIME +
                ", LAST_UPDATE_DATE_TIME=" + LAST_UPDATE_DATE_TIME +
                ", post_Id=" + post_Id +
                ", sender_Id=" + sender_Id +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", comments=" + comments +
                ", likes=" + likes +
                '}';
    }
}
