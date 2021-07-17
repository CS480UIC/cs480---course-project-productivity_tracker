package com.cs480.ProductivityTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserAPI 
{
	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	public static void printAllUsers()
	{
		try
        {
        	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/ProductivityTracker?"
				              + "user=root&password=a2bf77d41C");
			String sql = "SELECT * FROM User";
			preparedStatement = connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			System.out.println("Users:");
			System.out.println("user_id" + "\t" + "user_name" + "\t" + "email" + "\t" + "password");
		    while (resultSet.next()) 
		    {
		    	
		    	String user_id = resultSet.getString("user_id");
		    	String name = resultSet.getString("user_name");
		    	String email = resultSet.getString("email");
		    	String password = resultSet.getString("password");
		    	String team_position = resultSet.getString("team_position");
		    	//user_id | user_name      | email                | team_position     | password 
		    	System.out.println(user_id + "\t" + name + "\t" + email + "\t" + password);
		    }
        	
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
		finally
		{
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
		    } catch (Exception e) {

		    }
		  }
}
