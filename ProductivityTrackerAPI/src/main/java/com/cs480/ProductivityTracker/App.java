package com.cs480.ProductivityTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
	public static String MySqlDatabase = "ProductivityTracker";
	public static String MySqlUser = "root";
	public static String MySqlPassword = "a2bf77d41C";
	
	
	public static String LoggedInUser;
	
	public static void main( String[] args )
    {
		Scanner sc = new Scanner(System.in);
		String username;
		String password;
		
		System.out.println("Please Login");
		System.out.print("\\>username:");
		username = sc.nextLine();
		System.out.print("\\>password:");
		password = sc.nextLine();
		
		if(UserAPI.verifyUser(username, password))
		{
			System.out.println("Login Successful!");
			System.out.print("\\>");
		}
		else
		{
			System.out.println("This user does not exist");
			System.exit(1);
		}
		
		//Login Successful
		int option = -1;
		do
		{
			System.out.println("\nSelect an option:");
			System.out.println("1. Print All Users");
			System.out.println("2. <Unimplemented>");
			System.out.println("3. Exit");
			option = sc.nextInt();
			
			switch(option)
			{
				case 1:
				{
					UserAPI.printAllUsers();
				}
				
				default:
				{
					System.out.println("Invalied/Unimplemented Option");
				}
			
			}
			
		}while(option != 3);
		
		System.exit(1);
		
    }
	
    
    
}
