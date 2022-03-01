package com.cs480.ProductivityTracker;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpPrincipal;

public class MyHttpHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {
		URI requestURI = t.getRequestURI();

		String request = requestURI.toString();
		System.out.println(request);

		// remove leading /
		request = request.split("/")[1];

		// spilt into function and params
		String[] temp;
		temp = request.split("\\?");
		String function = temp[0];

		// get params
		String[] params;
		if (temp[1].equals("none")) // no params
		{
			params = null;
		} else if (!temp[1].contains("&")) // one param
		{
			params = new String[1];
			params[0] = temp[1];
		} else // multiple params
		{
			params = request.split("\\?")[1].split("&");
		}

		System.out.println("Function = " + function); // debug: print function

		// debug: print params
		if (params != null) {
			System.out.print("params = ");
			for (int i = 0; i < params.length; i++) {
				System.out.print(params[i] + ", ");
			}
			System.out.println();
		}

		String response = getResponseFromFunction(function, params);
		System.out.println("Response = " + response);
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();

	}

	private String getResponseFromFunction(String function, String[] params) {
		
		//System.out.println("in get resp");
		
		if (function.contains("User") && !function.contains("complex") && !function.contains("simple")) {
			return userCrudOperations(function, params);
		}
		
		else if (function.contains("Task")&& !function.contains("complex") && !function.contains("simple")) {
			return taskCrudOperations(function, params);
		}

		else if (function.contains("complex")) 
		{
			//System.out.println("in get resp complex");
			return complexQueryOperations(function, params);
		}
		else if(function.contains("simple"))
		{
			return simpleQueryOperations(function ,params);
		}
		
		//System.out.println("get resp null");
		return null;
	}
	
	private String simpleQueryOperations(String function, String[] params)
	{
		if(function.equals("simpleGetUserTeams"))
		{// Eg: http://192.168.0.110:8080/simpleGetUserTeams?7
			String result = SimpleQueriesAPI.getTeamsOfUser(params[0]);
			return result;
		}
		
		//getListOfUsersInTeam
		if(function.equals("simpleGetListOfUsersInTeam"))
		{// Eg: http://192.168.0.110:8080/simpleGetListOfUsersInTeam?7
			String result = SimpleQueriesAPI.getListOfUsersInTeam(params[0]);
			return result;
		}
		
		if(function.equals("simpleMarkTaskAsComplete"))
		{
			//Eg: http://192.168.0.110:8080/simpleMarkTaskAsComplete?6
			String result = Boolean.toString(SimpleQueriesAPI.markTaskAsComplete(params[0]));
			return result;
			
			
		}
		
		return null;
	}
	

	private String complexQueryOperations(String function, String[] params) {
		
		//System.out.println("in complex");
		
		// Produces tasks with the category of test cases give a team_id
		if (function.equals("complexGetTestCases")) {
			// Eg: http://192.168.1.69:8080/complexGetTestCases?1
			String result = ComplexQueriesAPI.getTeamTestCaseTasks(params[0]);
			return result;
		}

		// Produces highest priority tasks given a user_id
		if (function.equals("complexGetHP")) {
			// Eg: http://192.168.1.69:8080/complexGetHP?1007
			String result = ComplexQueriesAPI.getUsersHighestPriorityTasks(params[0]);
			return result;
		}

		// Produces a team's tasks where the uses position is Software Engineering
		if (function.equals("complexGetSWE")) {
			// Eg: http://192.168.1.69:8080/complexGetSWE?1
			String result = ComplexQueriesAPI.getTeamsSoftwareEngineeringTasks(params[0]);
			return result;
		}

		// Produces the email of the project manager given a team_id
		if (function.equals("complexGetPMEmail")) {
			// Eg: http://192.168.1.69:8080/complexGetPMEmail?1
			String result = ComplexQueriesAPI.getTeamsProjectManagerEmail(params[0]);
			return result;
		}

		// Produces all the tasks of a given team that are incomplete and order by high
		// priority
		if (function.equals("complexGetTeamsSorted")) {
			// Eg: http://192.168.1.69:8080/complexGetTeamsSorted?1
			String result = ComplexQueriesAPI.getTeamsSortedTask(params[0]);
			return result;
		}

		// Produces all the tasks of a given user that are incomplete and order by high
		// priority
		if (function.equals("complexGetUserSorted")) {
			// Eg: http://192.168.1.69:8080/complexGetUserSorted?1001
			//System.out.println("Inside complexGetUserSorted");
			String result = ComplexQueriesAPI.getUserSortedTask(params[0]);
			return result;
		}

		//System.out.println("Complex reuturn null");
		return null;
	}
	
	private String taskCrudOperations(String function, String[] params) {
		// Create
		if (function.equals("addTask")) {
			// Eg: http://192.168.0.110:8080/addUser?username&email&password
			boolean result = TaskAPI.addTask(params[0],params[1],params[2],params[3], params[4]);
			return Boolean.toString(result);
		}
		if (function.equals("deleteTask")) {
			// Eg: http://192.168.0.110:8080/deleteTask?10
			boolean result = TaskAPI.deleteTask(Integer.valueOf(params[0]));
			return Boolean.toString(result);
		}
		return null;
	}



	private String userCrudOperations(String function, String[] params) {
		// Create
		if (function.equals("addUser")) {
			// Eg: http://192.168.0.110:`8080/addUser?username&email&password
			boolean result = UserAPI.addUser(params[0], params[1], params[2]);
			return Boolean.toString(result);
		}

		// Read
		if (function.equals("getAllUsers")) {
			// Eg: http://192.168.0.110:8080/deleteUser?username
			String result = UserAPI.getAllUsers();
			return result;
		}

		// Delete
		if (function.equals("getUser")) {
			// Eg: http://192.168.0.110:8080/getUser?username
			String result = UserAPI.getUser(params[0]);
			return result;
		}

		// Read
		if (function.equals("verifyUser")) {
			// Eg: http://192.168.0.110:8080/verifyUser?admin&admin
			boolean result = UserAPI.verifyUser(params[0], params[1]);
			return Boolean.toString(result);
		}

		// Update
		if (function.equals("modifyUser")) {
			// Eg: (For password) http://192.168.0.110:8080/modifyUser?user&password&100
			// Eg: (For teamPosition)
			// http://192.168.0.110:8080/modifyUser?user&teamPosition&101
			// Eg: (For email) http://192.168.0.110:8080/modifyUser?user&email&102
			boolean result = UserAPI.modifyUser(params[0], params[1], Integer.parseInt(params[2]));
			return Boolean.toString(result);

		}

		// Delete
		if (function.equals("deleteUser")) {
			// Eg: http://192.168.0.110:8080/deleteUser?username
			boolean result = UserAPI.deleteUserByUserName(params[0]);
			return Boolean.toString(result);
		}

		return null;
	}

}
