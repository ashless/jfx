/*package com.example.geraficp;//add like

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LikeMenu extends Menu {
    private final User user;

    public LikeMenu(User user) {
        super(new String[]{"like", "Back"});
        this.user = user;
    }

    public void runMenu() throws SQLException {
        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    Post tweet = addLike();

                    //System.out.println(tweet.getText());
                    if (!Objects.isNull(tweet)) {
                        List<User> likes = tweet.getLikes();
                        boolean isLiked = isIn(user, likes);
                        if (isLiked) {
                            tweet.getLikes().remove(user);
                            System.out.println("you disliked this tweet/before you disliked this tweet");

                        } else {
                            //DataBaseConnection.like();
                            tweet.getLikes().add(user);
                            System.out.println("you liked this tweet");
                        }
                        java.util.Date javaDate = new java.util.Date();
                        Date mySQLDate = new Date(javaDate.getTime());

                        DataBaseConnection.like(tweet, tweet.getSender_Id(), mySQLDate);
                    }
                    break;

                case 2:
                    return;

            }
        }
    }

    private boolean isIn(User user, List<User> likes) {
        for (User user1 : likes) {
            if (user1.equals(user))
                return true;
        }
        return false;
    }


    public Post addLike() throws SQLException {

            List<Post> tweetList = new ArrayList<>(DataBaseConnection.findAllPost());

            List<String> texts = new ArrayList<>(DataBaseConnection.findAllPosts());

            texts.add("Back");
            String[] textTweets = texts.toArray(new String[0]);
            System.out.println("Enter your tweet for add like/dislike :");
            return new ShowUsersInformation<Post>(textTweets, tweetList, true).runMenu();

        }

    }





 */