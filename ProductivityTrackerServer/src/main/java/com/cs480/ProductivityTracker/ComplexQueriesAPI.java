package com.cs480.ProductivityTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class ComplexQueriesAPI {
	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	  public static String getTeamTestCaseTasks()
	  {
		  JSONArray ja = new JSONArray();
		  try {
		      executeQuery("SELECT Team.team_name, Task.task_name, Category.name as category ,User.user_name "
			      		+ "FROM Team "
			      		+ "JOIN Task ON Task.team_id = Team.team_id "
			      		+ "JOIN Category ON Category.category_id = Task.category_id "
			      		+ "JOIN User ON Task.user_id = User.user_id "
			      		+ "WHERE Team.team_id = 1 AND Category.category_id = 50;");
		      
		      while (resultSet.next()) {
		        String team_name = resultSet.getString("team_name");
		        String task_name = resultSet.getString("task_name");
		        String category = resultSet.getString("category");
		        String user_name = resultSet.getString("user_name");
		  
		        
		        //Create JSON Object
		        JSONObject jo = new JSONObject();
		        jo.put("team_name", team_name);
		        jo.put("task_name", task_name);
		        jo.put("category", category);
		        jo.put("user_name", user_name);
		        
		        //Add to JSONArray
		        ja.put(jo);	        
		      }
		    } catch (Exception e) {
		      System.out.println(e.getMessage());
		      return "error";
		    }
		  	finally {
		      close();
		      return ja.toString();
		    }  
	  }
	  
	private static void executeQuery(String sql) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + App.MySqlDatabase + "?" + "user="
					+ App.MySqlUser + "&password=" + App.MySqlPassword);
			preparedStatement = connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void executeUpdate(String sql) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + App.MySqlDatabase + "?" + "user="
					+ App.MySqlUser + "&password=" + App.MySqlPassword);
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
		    } catch (Exception e) {}
		  }
}

