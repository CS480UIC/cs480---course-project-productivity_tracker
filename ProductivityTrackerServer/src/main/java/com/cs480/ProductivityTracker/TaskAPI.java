package com.cs480.ProductivityTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TaskAPI {

	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	public static boolean deleteTask(Integer id) {
		try {
			executeUpdate("DELETE FROM Task WHERE task_id =" + id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			close();
			return false;
		}
		close();
		return true;
	}

	public static void searchTask(Integer id) {
		try {
			executeQuery("SELECT * FROM Task WHERE task_id ='" + id + "'");
			while (resultSet.next()) {
				String task_id = resultSet.getString("task_id");
				String task_name = resultSet.getString("task_name");
				String task_description = resultSet.getString("task_description");
				String user_id = resultSet.getString("user_id");
				String creation_date = resultSet.getString("creation_date");
				String dead_line_date = resultSet.getString("dead_line_date");
				String completed_date = resultSet.getString("completed_date");
				System.out.println("\n\n" + task_name + "(ID:" + task_id + ")");
				System.out.println("___________");
				System.out.println("Description:\t" + task_description);
				System.out.println("user_id:\t" + user_id);
				System.out.println("creation_date:\t" + creation_date);
				System.out.println("dead_line_date:\t" + dead_line_date);
				System.out.println("completed_date:\t" + completed_date);
				System.out.println("___________");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static boolean addTask(String taskName, String taskDesc, String userId, String teamId) {
		try {
			executeUpdate("INSERT INTO Task(task_name, task_description, user_id, team_id, is_completed) \n"
					+ "Values (\"" + taskName + "\",\"" + taskDesc + "\",\"" + userId + "\",\"" + teamId + "\",0);");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			close();
			return false;
		}
		close();
		return true;
	}

	public static void printAllTasks() {
		try {
			executeQuery("SELECT * FROM Task");
			System.out.println("All Tasks:");
			while (resultSet.next()) {
				String task_id = resultSet.getString("task_id");
				String task_name = resultSet.getString("task_name");
				String task_description = resultSet.getString("task_description");
				String user_id = resultSet.getString("user_id");
				String creation_date = resultSet.getString("creation_date");
				String dead_line_date = resultSet.getString("dead_line_date");
				String completed_date = resultSet.getString("completed_date");
				System.out.println("\n\n" + task_name + "(ID:" + task_id + ")");
				System.out.println("___________");
				System.out.println("Description:\t" + task_description);
				System.out.println("user_id:\t" + user_id);
				System.out.println("creation_date:\t" + creation_date);
				System.out.println("dead_line_date:\t" + dead_line_date);
				System.out.println("completed_date:\t" + completed_date);
				System.out.println("___________");
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
