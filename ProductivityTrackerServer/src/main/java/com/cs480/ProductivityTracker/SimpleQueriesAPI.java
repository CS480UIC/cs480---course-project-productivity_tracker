package com.cs480.ProductivityTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SimpleQueriesAPI 
{
	  private static Connection connect = null;
	  private static Statement statement = null;
	  private static PreparedStatement preparedStatement = null;
	  private static ResultSet resultSet = null;
	  
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
