package com.example.geraficp;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DataBaseConnection {
    private final String url = "jdbc:mysql://localhost:3306/data";
    private static final String username = "root";
    private static final String password = "ali12341234";

    public static void Connect() throws SQLException {


        System.out.println("c");
    }

    public static void like(Post post, Integer id, Date date) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        String query = "insert into likes values (?,?,?)";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, post.getPost_Id());
        preparedStmt.setInt(2, id);
        preparedStmt.setDate(3, date);
        preparedStmt.execute();
    }

    public static void addUser(User user) throws SQLException {

        String q = "SELECT COUNT(*) FROM users";
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        String query = "insert into users values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Statement stmt = con.createStatement();
        String quer = "select count(*) from users";
        ResultSet rs = stmt.executeQuery(quer);
        rs.next();
        int count = rs.getInt(1);

        PreparedStatement preparedStmt = con.prepareStatement(query);

        preparedStmt.setString(1, user.getFIRST_NAME());
        preparedStmt.setString(2, user.getLAST_NAME());
        preparedStmt.setString(3, user.getPHONE_NUMBER());
        preparedStmt.setString(4, user.getEMAIL());
        preparedStmt.setString(6, user.getBusiness());
        preparedStmt.setInt(5, user.getAGE());
        preparedStmt.setInt(7, count + 1);
        user.setUSER_ID(count + 1);
        preparedStmt.setString(8, user.getUSERNAME());
        preparedStmt.setString(9, user.getPASSWORD());
        preparedStmt.setString(10, user.getANSWER());
        preparedStmt.setString(11, user.getprofile());
        preparedStmt.execute();
    }

    public static Integer addPost(Post post) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        String query = "insert into posts values (?, ?, ?, ?, ?, ?)";
        Statement stmt = con.createStatement();
        String quer = "select count(*) from posts";
        ResultSet rs = stmt.executeQuery(quer);
        rs.next();
        int count = rs.getInt(1);
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, post.getText());
        preparedStmt.setDate(2, post.getCREATE_DATE_TIME());
        preparedStmt.setDate(3, post.getLAST_UPDATE_DATE_TIME());
        preparedStmt.setInt(4, count + 1);
        preparedStmt.setInt(5, post.getUser().getUSER_ID());
        preparedStmt.setString(6,post.getAddress());
        preparedStmt.execute();
        return count + 1;
    }

    public static void follow(User follower, User followed) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);

        String query = "insert into follow values (?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, follower.getUSER_ID());
        preparedStmt.setInt(2, followed.getUSER_ID());
        preparedStmt.execute();
    }


    public static void comment(Post post, User user, String text, Date date) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        String query = "insert into comments values (?, ?, ?, ?, ?, ?)";
        Statement stmt = con.createStatement();
        String quer = "select count(*) from comments";
        ResultSet rs = stmt.executeQuery(quer);
        rs.next();
        int count = rs.getInt(1);

        PreparedStatement preparedStmt = con.prepareStatement(query);

        preparedStmt.setString(1, text);
        preparedStmt.setDate(2, date);
        preparedStmt.setDate(3, date);
        preparedStmt.setInt(4, count + 1);
        preparedStmt.setInt(5, user.getUSER_ID());
        preparedStmt.setInt(6, post.getPost_Id());
        preparedStmt.execute();

    }

    public static void chat(String text, Date date, User sender, User receiver) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        String query = "insert into chat values (?, ?, ?, ?, ?, ?)";
        Statement stmt = con.createStatement();
        String quer = "select count(*) from chat";
        ResultSet rs = stmt.executeQuery(quer);
        rs.next();
        int count = rs.getInt(1);

        PreparedStatement preparedStmt = con.prepareStatement(query);

        preparedStmt.setString(1, text);
        preparedStmt.setDate(2, date);
        preparedStmt.setDate(3, date);
        preparedStmt.setInt(4, count + 1);
        preparedStmt.setInt(5, sender.getUSER_ID());
        preparedStmt.setInt(6, receiver.getUSER_ID());

        preparedStmt.execute();
    }

    public static ArrayList<String> findPostOfUser(User user) throws SQLException {

        ArrayList<String> posts = new ArrayList<>();

        String sql1 = "SELECT Text FROM posts where SENDER_ID = " + user.getUSER_ID();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql1)) {
            while (rs.next()) {
                posts.add(rs.getString("Text"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
    }

    public static User findByUsername(String Username, String Password) throws SQLException {

        String a = "\"";
        String sql = "SELECT * FROM users where USERNAME = " + a + Username + a;
        String sql1 = "SELECT * FROM users";
        System.out.println("Loging in...");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();
             ResultSet rs1 = stmt.executeQuery(sql)) {
            String pass = "";
            // loop through the result set
            System.out.println();
            while (rs1.next()) {
                pass = rs1.getString("PASSWORD");
                break;
            }
            //System.out.println(pass);
            if (Password.equals(pass)) {

                String FIRST_NAME = "";
                String LAST_NAME = "";
                String PHONE_NUMBER = "";
                String EMAIL = "";
                Integer AGE = 0;
                String Business = "";
                Integer USER_ID = 0;
                String USERNAME = "";
                String PASSWORD = "";

                FIRST_NAME = rs1.getString("FIRST_NAME");
                LAST_NAME = rs1.getString("LAST_NAME");
                PHONE_NUMBER = rs1.getString("PHONE_NUMBER");
                EMAIL = rs1.getString("EMAIL");
                AGE = rs1.getInt("AGE");
                Business = rs1.getString("Business");
                USER_ID = rs1.getInt("ID");
                USERNAME = rs1.getString("USERNAME");
                PASSWORD = rs1.getString("PASSWORD");


                return new User(FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL, AGE, Business, USER_ID, USERNAME, PASSWORD);


            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        return null;
    }

    public static ArrayList<String> findCommentOfUser(User user) {
        return null;
    }

    public static User findByUsername(String username1) {
        String a = "\"";
        String sql = "SELECT * FROM users where USERNAME = " + a + username1 + a;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();
             ResultSet rs1 = stmt.executeQuery(sql)) {

            String FIRST_NAME = "";
            String LAST_NAME = "";
            String PHONE_NUMBER = "";
            String EMAIL = "";
            Integer AGE = 0;
            String Business = "";
            Integer USER_ID = 0;
            String USERNAME = "";
            String PASSWORD = "";

            while (rs1.next()) {
                PASSWORD = rs1.getString("PASSWORD");
                USERNAME = rs1.getString("USERNAME");
                USER_ID = rs1.getInt("ID");
                Business = rs1.getString("Business");
                AGE = rs1.getInt("AGE");
                EMAIL = rs1.getString("EMAIL");
                PHONE_NUMBER = rs1.getString("PHONE_NUMBER");
                LAST_NAME = rs1.getString("LAST_NAME");
                FIRST_NAME = rs1.getString("FIRST_NAME");
            }

            return new User(FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL, AGE, Business, USER_ID, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    public static ArrayList<String> findAllPosts() throws SQLException {
        String sql = "SELECT * FROM posts";
        ArrayList<String> posts = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            String post = "";
            // loop through the result set
            System.out.println();
            while (rs.next()) {
                post = rs.getString("Text");
                posts.add(post);
            }
        }
        return posts;
    }

    public static ArrayList<Post> findAllPost() throws SQLException {
        String sql = "SELECT * FROM posts";
        String sql1 = "SELECT * FROM posts";
        ArrayList<Post> posts = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            String post = "";
            while (rs.next()) {
                post = rs.getString("Text");
                posts.add(new Post(rs.getDate("CREATE_DATE_TIME"),
                        rs.getDate("CREATE_DATE_TIME"), rs.getInt("ID"),
                        findUserById(rs.getInt("SENDER_ID")), rs.getString("Text")));
            }
        }

        return posts;
    }

    public static ArrayList<String> findfollowersOfUser(User user) throws SQLException {

        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<Integer> id = new ArrayList<>();
        String a = "\"";
        String sql = "SELECT FOLLoWER_ID FROM follow where FOLLOWED_ID = " + a + user.getUSER_ID() + a;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            System.out.println();
            while (rs.next()) {
                id.add(rs.getInt("FOLLoWER_ID"));
            }

            for (int i = 0; i < id.size(); i++) {
                String sql1 = "SELECT USERNAME FROM users where ID = " + a + id.get(i) + a;
                try (Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/data",
                        username, password);

                     Statement stmt1 = con1.createStatement();
                     ResultSet rs1 = stmt1.executeQuery(sql1)) {
                    String USERNAME = "";

                    // loop through the result set
                    while (rs1.next()) {
                        USERNAME = rs1.getString("USERNAME");
                    }
                    usernames.add(USERNAME);

                }

            }
            return usernames;
        }
    }

    public static ArrayList<String> findfollowingsOfUser(User user) throws SQLException {

        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<Integer> id = new ArrayList<>();
        String a = "\"";
        String sql = "SELECT FOLLOWED_ID FROM follow where FOLLoWER_ID = " + a + user.getUSER_ID() + a;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            System.out.println();
            while (rs.next()) {
                id.add(rs.getInt("FOLLOWED_ID"));
            }

            for (int i = 0; i < id.size(); i++) {
                String sql1 = "SELECT USERNAME FROM users where ID = " + a + id.get(i) + a;
                try (Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/data",
                        username, password);

                     Statement stmt1 = con1.createStatement();
                     ResultSet rs1 = stmt1.executeQuery(sql1)) {
                    String USERNAME = "";

                    // loop through the result set
                    while (rs1.next()) {
                        USERNAME = rs1.getString("USERNAME");
                    }
                    usernames.add(USERNAME);

                }

            }
            return usernames;
        }
    }

    public static ArrayList<String> findAllUsers() throws SQLException {
        ArrayList<String> users = new ArrayList<>();
        String sql = "SELECT USERNAME FROM users";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            String post = "";
            // loop through the result set
            System.out.println();
            while (rs.next()) {
                post = rs.getString("USERNAME");
                users.add(post);
            }
        }
        return users;
    }

    public static ArrayList<User> findAllUser() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);

             Statement stmt = con.createStatement();
             ResultSet rs1 = stmt.executeQuery(sql)) {
            String FIRST_NAME = "";
            String LAST_NAME = "";
            String PHONE_NUMBER = "";
            String EMAIL = "";
            Integer AGE = 0;
            String Business = "";
            Integer USER_ID = 0;
            String USERNAME = "";
            String PASSWORD = "";
            while (rs1.next()) {
                //post = rs.getString("USERNAME");
                FIRST_NAME = rs1.getString("FIRST_NAME");
                LAST_NAME = rs1.getString("LAST_NAME");
                PHONE_NUMBER = rs1.getString("PHONE_NUMBER");
                EMAIL = rs1.getString("EMAIL");
                AGE = rs1.getInt("AGE");
                Business = rs1.getString("Business");
                USER_ID = rs1.getInt("ID");
                USERNAME = rs1.getString("USERNAME");
                PASSWORD = rs1.getString("PASSWORD");

                users.add(new User(FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL, AGE, Business, USER_ID, USERNAME, PASSWORD));

            }
        }
        return users;
    }


    public static ArrayList<User> findfollowersOfUserU(User user) throws SQLException {

        ArrayList<User> usernames = new ArrayList<>();
        ArrayList<Integer> id = new ArrayList<>();
        String a = "\"";
        String sql = "SELECT FOLLoWER_ID FROM follow where FOLLOWED_ID = " + a + user.getUSER_ID() + a;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            System.out.println();
            while (rs.next()) {
                id.add(rs.getInt("FOLLoWER_ID"));
            }

            for (int i = 0; i < id.size(); i++) {
                String sql1 = "SELECT * FROM users where ID = " + a + id.get(i) + a;
                try (Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/data",
                        username, password);

                     Statement stmt1 = con1.createStatement();
                     ResultSet rs1 = stmt1.executeQuery(sql1)) {
                    String USERNAME = "";
                    String FIRST_NAME = "";
                    String LAST_NAME = "";
                    String PHONE_NUMBER = "";
                    String EMAIL = "";
                    Integer AGE = 0;
                    String Business = "";
                    Integer USER_ID = 0;
                    String PASSWORD = "";
                    // loop through the result set
                    while (rs1.next()) {
                        FIRST_NAME = rs1.getString("FIRST_NAME");
                        LAST_NAME = rs1.getString("LAST_NAME");
                        PHONE_NUMBER = rs1.getString("PHONE_NUMBER");
                        EMAIL = rs1.getString("EMAIL");
                        AGE = rs1.getInt("AGE");
                        Business = rs1.getString("Business");
                        USER_ID = rs1.getInt("ID");
                        USERNAME = rs1.getString("USERNAME");
                        PASSWORD = rs1.getString("PASSWORD");
                        usernames.add(new User(FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL, AGE, Business, USER_ID, USERNAME, PASSWORD));
                    }

                }

            }
            return usernames;
        }
    }

    public static ArrayList<User> findfollowingsOfUserU(User user) throws SQLException {

        ArrayList<User> usernames = new ArrayList<>();
        ArrayList<Integer> id = new ArrayList<>();
        String a = "\"";
        String sql = "SELECT FOLLOWED_ID FROM follow where FOLLoWER_ID = " + a + user.getUSER_ID() + a;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            System.out.println();
            while (rs.next()) {
                id.add(rs.getInt("FOLLOWED_ID"));
            }

            for (int i = 0; i < id.size(); i++) {
                String sql1 = "SELECT * FROM users where ID = " + a + id.get(i) + a;
                try (Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/data",
                        username, password);

                     Statement stmt1 = con1.createStatement();
                     ResultSet rs1 = stmt1.executeQuery(sql1)) {
                    String USERNAME = "";
                    String FIRST_NAME = "";
                    String LAST_NAME = "";
                    String PHONE_NUMBER = "";
                    String EMAIL = "";
                    Integer AGE = 0;
                    String Business = "";
                    Integer USER_ID = 0;
                    String PASSWORD = "";
                    // loop through the result set
                    while (rs1.next()) {
                        FIRST_NAME = rs1.getString("FIRST_NAME");
                        LAST_NAME = rs1.getString("LAST_NAME");
                        PHONE_NUMBER = rs1.getString("PHONE_NUMBER");
                        EMAIL = rs1.getString("EMAIL");
                        AGE = rs1.getInt("AGE");
                        Business = rs1.getString("Business");
                        USER_ID = rs1.getInt("ID");
                        USERNAME = rs1.getString("USERNAME");
                        PASSWORD = rs1.getString("PASSWORD");
                        usernames.add(new User(FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL, AGE, Business, USER_ID, USERNAME, PASSWORD));
                    }

                }

            }
            return usernames;
        }
    }

    public static void chat(User sender, User receiver, Chat chat) throws SQLException {

        java.util.Date javaDate = new java.util.Date();
        Date mySQLDate = new Date(javaDate.getTime());
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        String query = "insert into chat values (?, ?, ?, ?, ?, ?)";
        Statement stmt = con.createStatement();
        String quer = "select count(*) from chat";
        //Executing the query
        ResultSet rs = stmt.executeQuery(quer);
        //Retrieving the result
        rs.next();
        int count = rs.getInt(1);
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, chat.getTEXT());
        preparedStmt.setDate(2, mySQLDate);
        preparedStmt.setDate(3, mySQLDate);
        preparedStmt.setInt(4, count + 1);
        preparedStmt.setInt(6, sender.getUSER_ID());
        preparedStmt.setInt(5, receiver.getUSER_ID());
        preparedStmt.execute();


    }

    public static ArrayList<Comment> findAllComment(Post post) throws SQLException {
        String sql = "SELECT * FROM comments";
        ArrayList<Comment> comments = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            String comment = "";
            while (rs.next()) {
                comments.add(new Comment(rs.getDate("CREATE_DATE_TIME"),
                        rs.getDate("CREATE_DATE_TIME"), rs.getString("Text"),
                        post, findUserById(rs.getInt("SENDER_ID")), rs.getInt("ID")));
            }
        }

        return comments;
    }

    public static User findUserById(Integer Id) throws SQLException {

        String a = "";
        String sql = "SELECT * FROM users where Id = " + Id;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            try (Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/data",
                    username, password);
                 Statement stmt1 = con1.createStatement();
                 ResultSet rs1 = stmt1.executeQuery(sql)) {
                String FIRST_NAME = "";
                String LAST_NAME = "";
                String PHONE_NUMBER = "";
                String EMAIL = "";
                Integer AGE = 0;
                String Business = "";
                Integer USER_ID = 0;
                String USERNAME = "";
                String PASSWORD = "";

                // loop through the result set
                while (rs1.next()) {
                    FIRST_NAME = rs1.getString("FIRST_NAME");
                    LAST_NAME = rs1.getString("LAST_NAME");
                    PHONE_NUMBER = rs1.getString("PHONE_NUMBER");
                    EMAIL = rs1.getString("EMAIL");
                    AGE = rs1.getInt("AGE");
                    Business = rs1.getString("Business");
                    USER_ID = rs1.getInt("ID");
                    USERNAME = rs1.getString("USERNAME");
                    PASSWORD = rs1.getString("PASSWORD");
                }
                return new User(FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL, AGE, Business, USER_ID, USERNAME, PASSWORD);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static ArrayList<Post> findPostsOfUser(User user) throws SQLException {

        ArrayList<Post> posts = new ArrayList<>();

        String sql1 = "SELECT * FROM posts where SENDER_ID = " + user.getUSER_ID();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql1)) {
            while (rs.next()) {
                posts.add(new Post(rs.getDate("CREATE_DATE_TIME"),
                        rs.getDate("CREATE_DATE_TIME"), rs.getInt("ID"),
                        rs.getInt("SENDER_ID"), rs.getString("Text")));
            }
        }
        return posts;
    }

    public static ArrayList<User> findLikes(Post post) throws SQLException {
        String sql = "SELECT * FROM likes where POST_ID = " + post.getPost_Id();
        ArrayList<User> ss = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ss.add(findUserById(rs.getInt("USER_ID")));
            }
        }

        return ss;
    }

    public static void answer(String ans) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        String query = "insert into users (answer) values (?)";
        Statement stmt = con.createStatement();
        PreparedStatement preparedStmt = con.prepareStatement(query);

        preparedStmt.setString(1, ans);
        preparedStmt.execute();
    }

    public static void addGroup(GroupChat groupChat) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        Set setA = new HashSet();
        Statement stmt = con.createStatement();
        String quer = "select GROUP_ID from groupm";
        String query = "insert into groupm values (?, ?, ?, ?)";
        String query1 = "insert into `groups` values (?, ?, ?, ?, ?, ?)";
        ResultSet rs = stmt.executeQuery(quer);
        while (rs.next()) {
            setA.add(rs.getInt("GROUP_ID"));
        }
        PreparedStatement preparedStmt = con.prepareStatement(query);
        java.util.Date javaDate = new java.util.Date();
        Date mySQLDate = new Date(javaDate.getTime());

        if (chatExist(groupChat) == 0 && !(groupChat.getGROUP_NAME() == null)) {
            preparedStmt.setInt(1, setA.size() + 1);
            preparedStmt.setInt(2, groupChat.getUsers().get(0).getUSER_ID());
            preparedStmt.setString(3, null);
            preparedStmt.setString(4, "N");
            preparedStmt.execute();
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, setA.size() + 1);
            preparedStmt.setInt(2, groupChat.getUsers().get(1).getUSER_ID());
            preparedStmt.setString(3, null);
            preparedStmt.setString(4, "N");

            preparedStmt.execute();
        }
        preparedStmt = con.prepareStatement(query1);
        preparedStmt.setInt(1, setA.size() + 1);
        preparedStmt.setString(2, groupChat.getChats().get(0).getTEXT());
        preparedStmt.setInt(3, groupChat.getUsers().get(0).getUSER_ID());
        preparedStmt.setInt(4, chatExist(groupChat));
        preparedStmt.setDate(5, mySQLDate);
        preparedStmt.setDate(6, mySQLDate);
        preparedStmt.execute();


    }

    public static void addGroup2(GroupChat groupChat) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        Set setA = new HashSet();
        java.util.Date javaDate = new java.util.Date();
        java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
        Statement stmt = con.createStatement();
        String quer = "select GROUP_ID from groupm";
        ResultSet rs = stmt.executeQuery(quer);
        String query = "insert into groupm values (?, ?, ?, ?)";
        String query1 = "insert into `groups` values (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = con.prepareStatement(query);
        while (rs.next()) {
            setA.add(rs.getInt("GROUP_ID"));
        }
        preparedStmt.setInt(1, setA.size() + 1);
        preparedStmt.setInt(2, groupChat.getUsers().get(0).getUSER_ID());
        preparedStmt.setString(3, groupChat.getGROUP_NAME());
        preparedStmt.setString(4, "N");
        preparedStmt.execute();
        preparedStmt = con.prepareStatement(query1);

    }

    public static ArrayList<Chat> findChatOfGroup(User user) throws SQLException {
        ArrayList<Chat> groups = new ArrayList<>();
        String quer = "select count(*) from `groupm` ";
        int count;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data", username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(quer)) {
            rs.next();
            count = rs.getInt(1);

        }
        System.out.println(count);
        String sql = "SELECT * FROM `groups` where SENDER_ID = " + user.getUSER_ID();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data", username, password);

             Statement stmt1 = con.createStatement();
             ResultSet rs1 = stmt1.executeQuery(sql)) {
            while (rs1.next()) {
                groups.add(new Chat(rs1.getInt("SENDER_ID"), rs1.getString("TEXT"),
                        count));
            }
        }

        return groups;
    }

    public static ArrayList<String> findChatOfGroups(User user) throws SQLException {
        String sql = "SELECT * FROM `groups` where SENDER_ID = " + user.getUSER_ID();
        ArrayList<String> groups = new ArrayList<>();
        //System.out.println(sql);
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);

             Statement stmt1 = con.createStatement();
             ResultSet rs1 = stmt1.executeQuery(sql)) {
            while (rs1.next()) {
                groups.add(rs1.getString("TEXT"));
            }

        }

        return groups;
    }

    public static ArrayList<String> findChatOfGroups(GroupChat groupChat) throws SQLException {
        String sql = "SELECT * FROM `groups` where GROUP_ID = " + groupChat.getGROUP_ID();
        ArrayList<String> groups = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt1 = con.createStatement();

             ResultSet rs1 = stmt1.executeQuery(sql)) {
            {
                while (rs1.next()) {
                    groups.add(rs1.getString("TEXT"));
                }
            }
            return groups;
        }
    }

    public static ArrayList<Chat> findChatOfGroup(GroupChat groupChat) throws SQLException {
        String sql;

        if(groupChat.getUsers().size() == 2) {
            sql = "SELECT * FROM `groups` where GROUP_ID = " + chatExist(groupChat);

        }
        else {
            sql = "SELECT * FROM `groups` where GROUP_ID = " + groupChat.getGROUP_ID();

        }
        String quer = "select count(*) from `groups`";
        ArrayList<Chat> groups = new ArrayList<>();
        int count;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(quer)) {
            rs.next();
            count = rs.getInt(1);
        }
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt1 = con.createStatement();
             ResultSet rs1 = stmt1.executeQuery(sql)) {
            {
                while (rs1.next()) {
                    groups.add(new Chat(rs1.getInt("SENDER_ID"),
                            rs1.getString("TEXT"), count));
                }
            }
            return groups;
        }
    }



/*    public static Integer findChatByUsers(User user1, User user) throws SQLException{
        String sql = "SELECT * FROM groupm where USER_ID = " + user.getUSER_ID();
        int i = 0;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt1 = con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql)){
                {
                    while (rs1.next()) {
                        String quer = "select SENDER_ID from `groups` WHERE GROUP_ID = " + rs1.getInt("USER_ID");
                            break;
                    }
                }
            }


        Integer a = 0;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt1 = con.createStatement();
             ResultSet rs1 = stmt1.executeQuery(quer)){
            {
                while (rs1.next()) {
                    if(rs1.getInt("SENDER_ID") == user1.getUSER_ID()){
                        a = rs1.getInt("GROUP_ID");
                        break;
                    }
                }
            }
        }

             return a;
    }*/

    public static Integer chatExist(GroupChat groupChat) throws SQLException {
        Boolean check = false;
        Integer a = 0;
        String query = "SELECT * from `groupm` WHERE USER_ID = " + groupChat.getUsers().get(0).getUSER_ID();
        String query2 = "SELECT USER_ID from `groupm` WHERE GROUP_ID = ";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt1 = con.createStatement();
             ResultSet rs1 = stmt1.executeQuery(query)) {
            {
                if (groupChat.getUsers().size() == 2) {
                    while (rs1.next()) {
                        Statement stmt2 = con.createStatement();
                        ResultSet rs2 = stmt2.executeQuery(query2 + rs1.getInt("GROUP_ID"));
                        {
                            while (rs2.next()) {
                                if (rs2.getInt("USER_ID") == groupChat.getUsers().get(1).getUSER_ID()) {
                                    a = rs1.getInt("GROUP_ID");
                                }
                            }
                        }
                    }
                } else {
                    Set setA = new HashSet();
                    String quer = "select GROUP_ID from groupm";
                    ResultSet rs = stmt1.executeQuery(quer);
                    while (rs.next()) {
                        setA.add(rs.getInt("GROUP_ID"));
                    }
                    return setA.size();
                }
            }
        }


        return a;
    }

    public static User findByUsernameAndKey(String Username, String Password) throws SQLException {

        String a = "\"";
        String sql = "SELECT PASSWORD FROM users where USERNAME = " + a + Username + a;
        String sql1 = "SELECT * FROM users";
        System.out.println("Loging in...");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            String pass = "";
            // loop through the result set
            System.out.println();
            while (rs.next()) {
                pass = rs.getString("answer");

            }
            if (Password.equals(pass)) {
                try (Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/data",
                        username, password);

                     Statement stmt1 = con1.createStatement();
                     ResultSet rs1 = stmt1.executeQuery(sql1)) {

                    String FIRST_NAME = "";
                    String LAST_NAME = "";
                    String PHONE_NUMBER = "";
                    String EMAIL = "";
                    Integer AGE = 0;
                    String Business = "";
                    Integer USER_ID = 0;
                    String USERNAME = "";
                    String PASSWORD = "";

                    // loop through the result set
                    while (rs1.next()) {
                        FIRST_NAME = rs1.getString("FIRST_NAME");
                        LAST_NAME = rs1.getString("LAST_NAME");
                        PHONE_NUMBER = rs1.getString("PHONE_NUMBER");
                        EMAIL = rs1.getString("EMAIL");
                        AGE = rs1.getInt("AGE");
                        Business = rs1.getString("Business");
                        USER_ID = rs1.getInt("ID");
                        USERNAME = rs1.getString("USERNAME");
                        PASSWORD = rs1.getString("PASSWORD");

                    }

                    return new User(FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL, AGE, Business, USER_ID, USERNAME, PASSWORD);
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        return null;
    }

    public static Boolean memberExist(User user, GroupChat groupChat) throws SQLException {
        Boolean f = false;
        String quer = "select USER_ID from groupm where GROUP_ID = " + groupChat.getGROUP_ID();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();

             ResultSet rs = stmt.executeQuery(quer)) {
            while (rs.next()) {
                if (rs.getInt("USER_ID") == user.getUSER_ID()) {
                    f = true;
                }
            }
        }
        return f;
    }

    public static ArrayList<GroupChat> groupChatsOfUser(User user) throws SQLException {
        ArrayList<GroupChat> gps = new ArrayList<>();
        String quer = "select GROUP_ID from groupm where USER_ID = " + user.getUSER_ID();
        String query2 = "SELECT * from `groupm` WHERE GROUP_ID = ";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(quer)) {
            while (rs.next()) {

                Statement stmt2 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery(query2 + rs.getInt("GROUP_ID"));
                {
                    while (rs2.next()) {
                        //System.out.println(rs2.getInt("USER_ID"));
                        ArrayList<User> s = new ArrayList<>();
                        s.add(user);
                        s.add(findUserById(rs2.getInt("USER_ID")));
                        gps.add(new GroupChat(rs2.getInt("GROUP_ID"), s, new ArrayList<>()));
                    }
                }
            }
        }

        return gps;
    }

    public static ArrayList<GroupChat> groupsOfUser(User user) throws SQLException {
        ArrayList<GroupChat> ss = new ArrayList<>();
        String query2 = "SELECT * from `groupm` WHERE USER_ID = " + user.getUSER_ID();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query2)) {
            while (rs.next()) {
                if (!(rs.getString("GROUP_NAME") == null)) {
                    ArrayList<User> kk = new ArrayList<>();
                    kk.add(user);
                    GroupChat g = new GroupChat(rs.getInt("GROUP_ID"), kk, new ArrayList<Chat>());
                    g.setGROUP_NAME(rs.getString("GROUP_NAME"));
                    ss.add(g);

                }
            }
        }
        return ss;
    }

    public static void like(Comment comment, Integer id, Date date) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        String query = "insert into likesc values (?,?,?)";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt(1, comment.getCOMMENT_ID());
        preparedStmt.setInt(2, id);
        preparedStmt.setDate(3, date);
        preparedStmt.execute();
    }

    public static ArrayList<User> findLikes(Comment comment) throws SQLException {
        String sql = "SELECT * FROM likesc where POST_ID = " + comment.getCOMMENT_ID();
        ArrayList<User> ss = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ss.add(findUserById(rs.getInt("USER_ID")));
            }
        }

        return ss;
    }

    public static void comment(Comment comment, User user, String text, Date date) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        String query = "insert into commentsc values (?, ?, ?, ?, ?, ?)";
        Statement stmt = con.createStatement();
        String quer = "select count(*) from commentsc";
        ResultSet rs = stmt.executeQuery(quer);
        rs.next();
        int count = rs.getInt(1);

        PreparedStatement preparedStmt = con.prepareStatement(query);

        preparedStmt.setString(1, text);
        preparedStmt.setDate(2, date);
        preparedStmt.setDate(3, date);
        preparedStmt.setInt(4, count + 1);
        preparedStmt.setInt(5, user.getUSER_ID());
        preparedStmt.setInt(6, comment.getCOMMENT_ID());
        preparedStmt.execute();

    }

    public static void addToGroup(User user) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);

    }

    public static void addGroup3(GroupChat groupChat, User user) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        Set setA = new HashSet();
        java.util.Date javaDate = new java.util.Date();
        java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());
        Statement stmt = con.createStatement();
        String quer = "select GROUP_ID from groupm";
        ResultSet rs = stmt.executeQuery(quer);
        String query = "insert into groupm values (?, ?, ?, ?)";
        String query1 = "insert into `groups` values (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = con.prepareStatement(query);
        while (rs.next()) {
            setA.add(rs.getInt("GROUP_ID"));
        }
        preparedStmt.setInt(1, groupChat.getGROUP_ID());
        preparedStmt.setInt(2, user.getUSER_ID());
        preparedStmt.setString(3, groupChat.getGROUP_NAME());
        preparedStmt.setString(4, "N");
        preparedStmt.execute();
        preparedStmt = con.prepareStatement(query1);

    }

    public static ArrayList<User> findUsersOfGroup(GroupChat groupChat) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String query2 = "SELECT * from `groupm` WHERE GROUP_ID = " + groupChat.getGROUP_ID();

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query2);
        {
            while (rs.next()) {
                users.add(findUserById(rs.getInt("USER_ID")));
            }
        }

        return users;
    }

    public static GroupChat findGroup(String name) throws SQLException {
        String a = "\"";
        String query2 = "SELECT * from `groupm` WHERE GROUP_NAME = " + a + name + a;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query2);
        {
            while (rs.next()) {
                return new GroupChat(rs.getInt("GROUP_ID"), new ArrayList<User>(), new ArrayList<Chat>());
            }
        }
        return null;
    }

    public static void insertPic(String address) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        String query = "insert into posts address values (?)";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, address);
        preparedStmt.execute();
    }

    public static ArrayList<String> getPic(User user) throws SQLException {
        String a = "\"";
        String s;
        ArrayList<String> ss = new ArrayList<>();
        String query2 = "SELECT * from `posts` WHERE USER_ID = " + a + user.getUSER_ID() + a;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query2);
        {
            while (rs.next()) {
                ss.add(rs.getString("address"));
            }
        }
        return ss;
    }

    public static String getPic(Post post) throws SQLException {
        String a = "\"";
        String s;
        ArrayList<String> ss = new ArrayList<>();
        String query2 = "SELECT * from posts WHERE ID = " + a + post.getPost_Id() + a;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query2);
        {
            while (rs.next()) {
                return rs.getString("address");
            }
        }
        return null;
    }

    public static ArrayList<Comment> findAllComment(Comment post) throws SQLException {
        String sql = "SELECT * FROM commentsc";
        ArrayList<Comment> comments = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);

             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            String comment = "";
            while (rs.next()) {
                comments.add(new Comment(rs.getDate("CREATE_DATE_TIME"),
                        rs.getDate("CREATE_DATE_TIME"), rs.getString("Text"),
                        post, findUserById(rs.getInt("SENDER_ID")), rs.getInt("ID")));
            }
        }

        return comments;
    }

    public static void setProfile(User user, String string) throws SQLException {
        String a = "\"";
        String query = "UPDATE users SET profile = (?) WHERE USERNAME = " + a + user.getUSERNAME() + a;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString(1, string);
        preparedStmt.execute();
    }

    public static String getProfile(User user) throws SQLException {
        String a = "\"";
        String s;
        String query2 = "SELECT * from `users` WHERE ID = " + a + user.getUSER_ID() + a;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query2);
        {
            while (rs.next()) {
                return rs.getString("profile");
            }
        }
        return null;

    }


/*    public static Integer hasAccess(User user, GroupChat groupChat) throws SQLException{
        Integer z = 0;
        String a = "\"";
        String query2 = "SELECT * from `groupm` WHERE GROUP_NAME = " + a + groupChat.getGROUP_NAME() + a;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data",
                username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query2);
        {
            while (rs.next()) {
                if(rs.getString(""))
            }
        }

        return z;
    }*/
}

