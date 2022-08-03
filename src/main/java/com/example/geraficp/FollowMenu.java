/*package com.example.geraficp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class FollowMenu extends Menu {

    private final User user;

    public FollowMenu(User user) {
        super(new String[]{"follow", "Show My followers", "Show MY followings","Back"});
        this.user = user;
    }
    public void runMenu() throws SQLException {

        while (true) {
            print();
            switch (chooseOperation()) {
                case 1:
                    User chosenUser = follow();

                    //System.out.println(tweet.getText());
                    if (!Objects.isNull(chosenUser)){
                        chosenUser.setFollowers(DataBaseConnection.findfollowersOfUserU(chosenUser));
                        for(User user : chosenUser.getFollowers()){
                            System.out.println(user.getFIRST_NAME());
                        }
                        ArrayList<User> followers = chosenUser.getFollowers();
                        boolean isFollow = isIn(user, followers);
                        if (isFollow) {
                            chosenUser.getFollowers().remove(user);
                            System.out.println("you followed this user befor / now we unfollow it");

                        } else {

                            chosenUser.getFollowers().add(user);
                            System.out.println("now you follow this User");
                        }

                        DataBaseConnection.follow(user,chosenUser);
                    }
                    break;
                case 2:
                    for (String followers : DataBaseConnection.findfollowersOfUser(user)) {
                        System.out.println(followers);
                    }
                    break;
                case 3:
                    for (String followings : DataBaseConnection.findfollowingsOfUser(user)) {
                        System.out.println(followings);
                    }
                    break;
                case 4:
                    return;
            }
        }

    }
    private boolean isIn(User user, java.util.List<User> followers) {
        for (User user1 : followers) {
            if (user1.equals(user))
                return true;
        }
        return false;
    }

    public User follow() throws SQLException {

        ArrayList<User> UserList = new ArrayList<User>(DataBaseConnection.findAllUser());

        ArrayList<String> texts = new ArrayList<String>(DataBaseConnection.findAllUsers());

        texts.add("Back");
        String[] textfollow = texts.toArray(new String[0]);
        System.out.println("Enter your User to add in/remove from your followings :");
        return new ShowUsersInformation<User>(textfollow, UserList, true).runMenu();

    }
}

 */