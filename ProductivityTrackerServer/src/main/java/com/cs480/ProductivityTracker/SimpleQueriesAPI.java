package com.cs480.ProductivityTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class SimpleQueriesAPI 
{
	  private static Connection connect = null;
	  private static Statement statement = null;
	  private static PreparedStatement preparedStatement = null;
	  private static ResultSet resultSet = null;
	  
	  
	  
	  //Returns all the teams a specific user is a part of
	  public static String getTeamsOfUser(String userId){
		  JSONArray ja = new JSONArray();
		  try {
		      executeQuery("SELECT t.team_id, t.team_name "
		      		+ "FROM Team AS t "
		      		+ "JOIN TeamUser AS tu "
		      		+ "ON tu.team_id = t.team_id "
		      		+ "JOIN User as u "
		      		+ "ON u.user_id = tu.user_id "
		      		+ "WHERE u.user_id = " + userId + ";");
		      
		      while (resultSet.next()) {
		    	String team_id = resultSet.getString("team_id");
		        String team_name = resultSet.getString("team_name");

		        if(team_id.equals(null)) team_id = "NA";
		        if(team_name.equals(null)) team_name = "NA";
		        
		        //Create JSON Object
		        JSONObject jo = new JSONObject();
		        jo.put("team_id", team_id);
		        jo.put("team_name", team_name);

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
