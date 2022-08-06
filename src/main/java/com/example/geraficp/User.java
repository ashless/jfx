package com.example.geraficp;

import java.util.ArrayList;

public class User {
    private String FIRST_NAME;
    private String LAST_NAME;
    private String PHONE_NUMBER;
    private String EMAIL;
    private Integer AGE;
    private String Business;
    private String BIRTH_DAY;
    private Integer USER_ID;
    private String USERNAME;
    private String PASSWORD;
    private String ANSWER;
    private ArrayList<User> followers;
    private ArrayList<User> followings;
    private ArrayList<Post> posts;
    private String profile;

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public User (){

    }

    public User(String FIRST_NAME, String LAST_NAME, String PHONE_NUMBER, String EMAIL,
                Integer AGE, String business, String USERNAME, String PASSWORD, String ANSWER) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.EMAIL = EMAIL;
        this.AGE = AGE;
        this.Business = business;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.ANSWER = ANSWER;
        this.followers = null;
        this.followings = null;
    }

    public User(String FIRST_NAME, String LAST_NAME, String PHONE_NUMBER, String EMAIL, Integer AGE,
                String business, Integer USER_ID, String USERNAME, String PASSWORD) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.EMAIL = EMAIL;
        this.AGE = AGE;
        Business = business;
        this.USER_ID = USER_ID;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;

    }

    public User(String FIRST_NAME, String LAST_NAME, String PHONE_NUMBER, String EMAIL, Integer AGE,
                String business, Integer USER_ID, String USERNAME, String PASSWORD, String ANSWER) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.EMAIL = EMAIL;
        this.AGE = AGE;
        Business = business;
        this.USER_ID = USER_ID;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.ANSWER = ANSWER;
    }

    public String getBusiness() {
        return Business;
    }

    public void setBusiness(String business) {
        this.Business = business;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public ArrayList<User> getFollowings() {
        return followings;
    }

    public void setFollowings(ArrayList<User> followings) {
        this.followings = followings;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public void setPHONE_NUMBER(String PHONE_NUMBER) {
        this.PHONE_NUMBER = PHONE_NUMBER;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public Integer getAGE() {
        return AGE;
    }

    public void setAGE(Integer AGE) {
        this.AGE = AGE;
    }

    public String getBIRTH_DAY() {
        return BIRTH_DAY;
    }

    public void setBIRTH_DAY(String BIRTH_DAY) {
        this.BIRTH_DAY = BIRTH_DAY;
    }

    public Integer getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(Integer USER_ID) {
        this.USER_ID = USER_ID;
    }

    @Override
    public String toString() {
        return getFIRST_NAME() + " " + getLAST_NAME() + "\n"
                + getUSERNAME() + "\n";
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }

    public String getprofile() {
        return profile;
    }
    public void setprofile(String profile){
        this.profile = profile;
    }
}
