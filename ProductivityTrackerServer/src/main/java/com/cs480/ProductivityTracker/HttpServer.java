package com.cs480.ProductivityTracker;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer 
{
	public static int PORT = 8080;
	public static int IP;
	
	ServerSocket serverSocket;
	InputStream inputStream;
	OutputStream outputStream;
	
	public void run()
	{
		/*try 
		{
			serverSocket = new ServerSocket(PORT);
			Socket soc = serverSocket.accept();
			
			inputStream = soc.getInputStream();
			outputStream = soc.getOutputStream();
			
			//TODO: Read
					
			//TODO: Write
			String html = "<html><head><title>Server LOL</title></head></html>";
			
			final String CRLF = "\n\r"; //13,10
			
			String response = 
					"HTTP/1.1 200 OK" + CRLF +//Status line
					"Content Length: " + html.getBytes().length + CRLF + 
					CRLF +
					html +
					CRLF + CRLF;
			outputStream.write(response.get);
			
			inputStream.close();
			outputStream.close();
			soc.close();
			serverSocket.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}*/
	}
	
	

}
