package com.cs480.ProductivityTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TeamAPI {

  private static Connection connect = null;
  private static Statement statement = null;
  private static PreparedStatement preparedStatement = null;
  private static ResultSet resultSet = null;

  public static boolean addTeam(String team_name) {
    try {
      executeUpdate("INSERT INTO Team(team_name) VALUES('" + team_name + "');");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      close();
      return false;
    }
    close();
    return true;
  }

  public static boolean deleteTeam(Integer id) {
    try {
      executeUpdate("DELETE FROM Team WHERE team_id =" + id);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      close();
      return false;
    }
    close();
    return true;
  }

  public static void updateTeamName(Integer team_id, String newTeamName) {
    try {
      executeUpdate(
        "UPDATE Team SET team_name ='" +
        newTeamName +
        "' WHERE team_id =" +
        team_id
      );

      printAllTeams();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      close();
    }
  }

  public static void searchTeam(Integer id) {
    try {
      executeQuery("SELECT * FROM Team WHERE team_id ='" + id + "'");
      while (resultSet.next()) {
        String team_id = resultSet.getString("team_id");
        String team_name = resultSet.getString("team_name");
        System.out.println("team_id: " + team_id + " | " + team_name);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void printAllTeams() {
    try {
      executeQuery("SELECT * FROM Team");
      System.out.println("All Teams:");
      while (resultSet.next()) {
        String team_id = resultSet.getString("team_id");
        String team_name = resultSet.getString("team_name");
        System.out.println("(" + team_id + ") - " + team_name);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      close();
    }
  }

  private static void executeQuery(String sql) {
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
      resultSet = preparedStatement.executeQuery();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static void executeUpdate(String sql) {
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
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
