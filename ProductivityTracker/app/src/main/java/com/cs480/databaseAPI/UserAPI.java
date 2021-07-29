package com.cs480.databaseAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserAPI
{
    public static final int UPDATE_PASSWORD = 100;
    public static final int UPDATE_TEAM_POSITION = 101;
    public static final int UPDATE_EMAIL = 102;

    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public static boolean verifyUser(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connect =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/" +
                                    App.MySqlDatabase +
                                    "?" +
                                    "user=" +
                                    App.MySqlUser +
                                    "&password=" +
                                    App.MySqlPassword
                    );
            String sql = "SELECT * FROM User";
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String rUsername = resultSet.getString("user_name");
                String rPassword = resultSet.getString("password");
                if (username.equals(rUsername) && password.equals(rPassword)) {
                    close();
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }

        return false;
    }

    public static boolean deleteUserByUserName(String username) {
        //DELETE FROM table_name
        //WHERE condition;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connect =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/" +
                                    App.MySqlDatabase +
                                    "?" +
                                    "user=" +
                                    App.MySqlUser +
                                    "&password=" +
                                    App.MySqlPassword
                    );
            String sql = "DELETE FROM User WHERE user_name = '" + username + "'";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            close();
            return false;
        }
        close();
        return true;
    }

    // ! Do we need to have user_id if it is autoincremented
    public static boolean addUser(
            int user_id,
            String user_name,
            String email,
            String password
    ) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connect =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/" +
                                    App.MySqlDatabase +
                                    "?" +
                                    "user=" +
                                    App.MySqlUser +
                                    "&password=" +
                                    App.MySqlPassword
                    );
            String sql =
                    "INSERT INTO User(user_id, user_name, email, password) " +
                            "VALUES (" +
                            user_id +
                            "," +
                            "'" +
                            user_name +
                            "'," +
                            "'" +
                            email +
                            "'," +
                            "'" +
                            password +
                            "'" +
                            ");";
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            close();
            return false;
        }
        close();
        return true;
    }

    public static boolean modifyUser(String userName, String update, int option) {
        //UPDATE table_name
        //SET column1 = value1, column2 = value2, ...
        //WHERE condition;

        String sql = "";
        switch (option) {
            case UPDATE_PASSWORD:
            {
                sql =
                        "UPDATE User SET password = '" +
                                update +
                                "' WHERE user_name = '" +
                                userName +
                                "'";
                break;
            }
            case UPDATE_EMAIL:
            {
                sql =
                        "UPDATE User SET email = '" +
                                update +
                                "' WHERE user_name = '" +
                                userName +
                                "'";
                break;
            }
            case UPDATE_TEAM_POSITION:
            {
                sql =
                        "UPDATE User SET team_position = '" +
                                update +
                                "' WHERE user_name = '" +
                                userName +
                                "'";
                break;
            }
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connect =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/" +
                                    App.MySqlDatabase +
                                    "?" +
                                    "user=" +
                                    App.MySqlUser +
                                    "&password=" +
                                    App.MySqlPassword
                    );
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            close();
            return false;
        }
        close();
        return true;
    }

    public static void printAllUsers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connect =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/" +
                                    App.MySqlDatabase +
                                    "?" +
                                    "user=" +
                                    App.MySqlUser +
                                    "&password=" +
                                    App.MySqlPassword
                    );
            String sql = "SELECT * FROM User";
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            System.out.println("Users:");
            System.out.println(
                    "user_id" + "\t" + "user_name" + "\t" + "email" + "\t" + "password"
            );
            while (resultSet.next()) {
                String user_id = resultSet.getString("user_id");
                String name = resultSet.getString("user_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String team_position = resultSet.getString("team_position");
                //user_id | user_name      | email                | team_position     | password
                System.out.println(
                        user_id + "\t" + name + "\t" + email + "\t" + password
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }

    public static void printUser(String user_name) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connect =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/" +
                                    App.MySqlDatabase +
                                    "?" +
                                    "user=" +
                                    App.MySqlUser +
                                    "&password=" +
                                    App.MySqlPassword
                    );
            String sql = "SELECT * FROM User WHERE user_name = '" + user_name + "'";
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            System.out.println("Users:");
            System.out.println(
                    "user_id" + "\t" + "user_name" + "\t" + "email" + "\t" + "password"
            );
            while (resultSet.next()) {
                String user_id = resultSet.getString("user_id");
                String name = resultSet.getString("user_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String team_position = resultSet.getString("team_position");
                //user_id | user_name      | email                | team_position     | password
                System.out.println(
                        user_id + "\t" + name + "\t" + email + "\t" + password
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }

    private static void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {}
    }
}
