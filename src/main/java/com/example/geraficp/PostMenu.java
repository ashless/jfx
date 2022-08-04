/*package com.example.geraficp;

import java.sql.SQLException;

public class PostMenu extends Menu {
    private final User user;

    // private final PostService tweetService;
    // public final UserService userService = ApplicationContext.getUserService();
    public PostMenu(User user) {
        super(new String[]{"Add Post", "My Posts", "Back"});
        this.user = user;
        //   this.tweetService = tweetService;
    }


    public void runMenu() throws SQLException {
        // User user1 = findById(user.getId());
        while (true) {
            print();
            Post post = new Post();
            switch (chooseOperation()) {
                case 1:
                    if (user.getBusiness().equals("B")) {
                        post.setText("Advertisement: " + new Input(
                                "Enter your advertise text :",
                                "Your text must be a minimum of 280 characters",
                                "", null).getInputTextString());
                    } else {

                        post.setText(new Input(
                                "Enter your text :",
                                "Your text must be a minimum of 280 characters",
                                "", null).getInputTextString());
                    }
                    java.util.Date javaDate = new java.util.Date();
                    java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
                    post.setCREATE_DATE_TIME(mySQLDate);
                    post.setLAST_UPDATE_DATE_TIME(mySQLDate);
                    post.setUser(user);
                    DataBaseConnection.addPost(post);
                    break;
                case 2:
                    System.out.println("***List of Your Post:***");
                    for (Post posts : DataBaseConnection.findPostsOfUser(user)) {
                        System.out.println(posts.getText());
                        System.out.println("***List of comment:***");
                        for (Comment comments:DataBaseConnection.findAllComment(posts)) {
                            System.out.println(comments.getTextComment());
                        }
                        System.out.println("***List of likes:***");
                        DataBaseConnection.findLikes(posts);
                    }

                    break;
                case 3:
                    return;
            }
        }
    }
}


}
*/

