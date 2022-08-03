/*package com.example.geraficp;//add comment mondeh

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentMenu extends Menu {
    private final User user;

    public CommentMenu(User user) {
        super(new String[]{"Add Comment", "Show My Comments", "Back"});
        this.user = user;
    }

    public void runMenu() throws SQLException {

        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:

                    Post post = addComment();
                    java.util.Date javaDate = new java.util.Date();
                    java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
                    String text = new Input("Enter your comment").getInputTextString();

                    if (!Objects.isNull(post)) {
                        DataBaseConnection.comment(post, user, text, mySQLDate);
                        break;
                    }
                        case 2:
                            for (String comment : DataBaseConnection.findCommentOfUser(user)) {
                                System.out.println(comment);
                            }
                            break;
                        case 3:
                            return;
                    }
            }
        }



        public Post addComment () throws SQLException {

            List<Post> tweetList = new ArrayList<>(DataBaseConnection.findAllPost());

            List<String> texts = new ArrayList<>(DataBaseConnection.findAllPosts());
            texts.add("Back");
            String[] textTweets = texts.toArray(new String[0]);

            System.out.println("Enter your post to add comment :");
            return new ShowUsersInformation<Post>(textTweets, tweetList, true).runMenu();

        }


    }


 */