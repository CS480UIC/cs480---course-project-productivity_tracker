package com.cs480.ProductivityTracker;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpPrincipal;

public class MyHttpHandler implements HttpHandler
{

	@Override
	public void handle(HttpExchange t) throws IOException 
	{
		 URI requestURI = t.getRequestURI();
	     //printRequestInfo(t);
		  // "/func1?param1&param2&param3"
	     String request = requestURI.toString();
	     System.out.println(request);
	     
	     request = request.split("/")[1];
	     
	     String function = request.split("\\?")[0];
	     String[] params = request.split("\\?")[1].split("&");
	     System.out.println("Function = " + function);
	     System.out.println("Params = " + params[0] + ", " + params[1]);
	     
	     String response = getResponseFromFunction(function, params);
	     System.out.println("Response = " + response);
	     t.sendResponseHeaders(200, response.length());
	     OutputStream os = t.getResponseBody();
	     os.write(response.getBytes());
	     os.close();
		
	}
	
	  private static void printRequestInfo(HttpExchange exchange) {
	      System.out.println("-- headers --");
	      Headers requestHeaders = exchange.getRequestHeaders();
	      requestHeaders.entrySet().forEach(System.out::println);

	      System.out.println("-- principle --");
	      HttpPrincipal principal = exchange.getPrincipal();
	      System.out.println(principal);

	      System.out.println("-- HTTP method --");
	      String requestMethod = exchange.getRequestMethod();
	      System.out.println(requestMethod);

	      System.out.println("-- query --");
	      URI requestURI = exchange.getRequestURI();
	      String query = requestURI.getQuery();
	      System.out.println(query);
	  }
	  
	  private String getResponseFromFunction(String function, String[] params)
      {

		  if(function.equals("verifyUser"))
		  {
			 //Eg: http://192.168.0.110:8080/verifyUser?admin&admin
			 boolean result = UserAPI.verifyUser(params[0], params[1]);		 
			 return Boolean.toString(result);
		  }
		  if(function.equals("addUser"))
		  {
			  //Eg: http://192.168.0.110:8080/addUser?admin3&admin3&admin3
			  boolean result = UserAPI.addUser(params[0], params[1], params[2]);
			  return Boolean.toString(result);
		  }
		  
		  
		return null;
		  
      }
	  
	  
	  

}


