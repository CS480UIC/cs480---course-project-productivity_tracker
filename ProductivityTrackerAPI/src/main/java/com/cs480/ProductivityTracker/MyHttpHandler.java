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
	     printRequestInfo(t);
		  // "/func1?param1&param2&param3"
	     String request = requestURI.toString();
	     System.out.println(request);
	     
	     request = request.split("/")[1];
	     System.out.println(request);
	     
	     String function = request.split("\\?")[0];
	     String[] params = request.split("\\?")[1].split("&");
	     System.out.println("Function = " + function);
	     System.out.println("Params = " + params[0] + ", " + params[1]);
	     
	     String response = getResponseFromFunction(function, params);
/*
 *	  // "func1?param1&param2&param3"
	     request = request.split("/")[1];
	     
	  //[0] = func1, [1] => param1, [2] => param2..."
	     String functionCall = request.split("?")[0];
	     String[] params = request.split("?")[1].split("&");
	     
	     System.out.println("Function = " + functionCall);
	     System.out.println("Params = " + params);
 */

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
			 boolean result = UserAPI.verifyUser(params[0], params[1]);		 
			 return Boolean.toString(result);
		  }
		  
		return null;
		  
      }
	  
	  
	  

}


