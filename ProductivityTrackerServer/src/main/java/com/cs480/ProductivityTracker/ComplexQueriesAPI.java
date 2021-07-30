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

	  public static String getTeamTestCaseTasks(String teamID){
		  JSONArray ja = new JSONArray();
		  try {
		      executeQuery("SELECT Team.team_name, Task.task_name, Category.name as category ,User.user_name "
			      		+ "FROM Team "
			      		+ "JOIN Task ON Task.team_id = Team.team_id "
			      		+ "JOIN Category ON Category.category_id = Task.category_id "
			      		+ "JOIN User ON Task.user_id = User.user_id "
			      		+ "WHERE Team.team_id = "+teamID+" AND Category.category_id = 50;");
		      
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
	  
	  public static String getUsersHighestPriorityTasks(String userId){
		  JSONArray ja = new JSONArray();
		  try {
		      executeQuery("SELECT Task.task_name, Task.task_description, PriorityDescription.description, PriorityDescription.priority, User.user_name, User.user_id\n"
		      		+ "FROM User\n"
		      		+ "JOIN Task ON Task.user_id = User.user_id\n"
		      		+ "JOIN PriorityDescription ON PriorityDescription.priority = Task.priority\n"
		      		+ "WHERE PriorityDescription.priority = (SELECT MIN(PriorityDescription.priority)\n"
		      		+ "FROM User\n"
		      		+ "JOIN Task ON Task.user_id = User.user_id\n"
		      		+ "JOIN PriorityDescription ON PriorityDescription.priority = Task.priority\n"
		      		+ "WHERE User.user_id = "+userId+") AND User.user_id = "+userId+"");
		      
		      while (resultSet.next()) {
		        String task_name = resultSet.getString("task_name");
		        String task_description = resultSet.getString("task_description");
		        String description = resultSet.getString("description");
		        String priority = resultSet.getString("priority");
		        String user_name = resultSet.getString("user_name");
		        String user_id = resultSet.getString("user_id");

		        
		        //Create JSON Object
		        JSONObject jo = new JSONObject();
		        jo.put("task_name", task_name);
		        jo.put("task_description", task_description);
		        jo.put("description", description);
		        jo.put("priority", priority);
		        jo.put("user_name", user_name);
		        jo.put("user_id", user_id);
		        
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
	  
	  public static String getTeamsSoftwareEngineeringTasks(String teamId){
		  JSONArray ja = new JSONArray();
		  try {
		      executeQuery("SELECT User.user_name, Task.task_name, Task.task_description \n"
		      		+ "FROM Team\n"
		      		+ "JOIN TeamUser on TeamUser.team_id = Team.team_id\n"
		      		+ "JOIN User On  User.user_id = TeamUser.user_id\n"
		      		+ "JOIN Task ON Task.user_id = User.user_id\n"
		      		+ "WHERE Team.team_id = "+teamId+" AND User.team_position = \"Software Engineer\";\n");
		      
		      while (resultSet.next()) {
		        String user_name = resultSet.getString("user_name");
		        String task_name = resultSet.getString("task_name");
		        String task_description = resultSet.getString("task_description");

		        //Create JSON Object
		        JSONObject jo = new JSONObject();
		        jo.put("user_name", user_name);
		        jo.put("task_name", task_name);
		        jo.put("task_description", task_description);
		        
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

