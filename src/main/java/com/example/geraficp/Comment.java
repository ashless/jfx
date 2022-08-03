package com.example.geraficp;

import java.time.LocalDateTime;
import java.util.Date;

public class Comment {
    public static final String TEXT_COMMENT = "text_comment";
    private Date CREATE_DATE_TIME;
    private Date LAST_UPDATE_DATE_TIME;
    private String textComment;
    private LocalDateTime createDateTime;
    private LocalDateTime lastUpdateDateTime;
    private Post tweet;
    private User user;
    private  Integer COMMENT_ID;

    public Comment(Date CREATE_DATE_TIME, Date LAST_UPDATE_DATE_TIME, String textComment, Post tweet, User user, Integer COMMENT_ID) {
        this.CREATE_DATE_TIME = CREATE_DATE_TIME;
        this.LAST_UPDATE_DATE_TIME = LAST_UPDATE_DATE_TIME;
        this.textComment = textComment;
        this.tweet = tweet;
        this.user = user;
        this.COMMENT_ID = COMMENT_ID;
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

    public User getUser() {
        return user;
    }

    public Integer getCOMMENT_ID() {
        return COMMENT_ID;
    }

    public void setCOMMENT_ID(Integer COMMENT_ID) {
        this.COMMENT_ID = COMMENT_ID;
    }
    /*  @Column(name = TEXT_COMMENT)

    @Column(name = CREATE_DATE_TIME, nullable = false)

    @Column(name = LAST_UPDATE_DATE_TIME, nullable = false)

    @ManyToOne
    @JoinColumn(name = "tweet_id")

    @ManyToOne
    @JoinColumn(name = "user_id")
*/

//    @Override
/*    public String toString() {
        return "@" + user.getUsername() + ":\n" +
                textComment + "\n";
    }*/

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(LocalDateTime lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public Post getTweet() {
        return tweet;
    }

    public void setTweet(Post tweet) {
        this.tweet = tweet;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public String getTextComment() {
        return textComment;
    }
}
