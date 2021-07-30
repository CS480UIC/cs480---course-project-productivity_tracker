package com.cs480.ProductivityTracker;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHttpHandler2 implements HttpHandler
{

	@Override
	public void handle(HttpExchange t) throws IOException 
	{
		// TODO Auto-generated method stub
        String response = "{}";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
		
	}

}