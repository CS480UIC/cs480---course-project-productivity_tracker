package com.cs480.ProductivityTracker;

import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
 
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
/**
 * Hello world!
 *
 */
public class App {

  public static String MySqlDatabase = config.MySqlDatabase;
  public static String MySqlUser = config.MySqlUser;
  public static String MySqlPassword = config.MySqlPassword;

  
  public static String LoggedInUser;

  public static void main(String[] args) 
  {
	  //consoleApp();
	  startHttpServer();
  }
  
  public static void startHttpServer()
  {
	  
	  System.out.println("Start");
	  //HttpServer hs = new HttpServer();
	  //hs.run();
	  try
	  {
		  HttpServer server = HttpServer.create(new InetSocketAddress(config.IP, config.Port), 0);
	      server.createContext("/", new MyHttpHandler());
	      server.setExecutor(null); 
	      server.start();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
      
      
	  System.out.println("End");
  }
 

  
  public static void consoleApp()
  {
	  Scanner sc = new Scanner(System.in);
	    String username;
	    String password;

	    System.out.println("Please Login");
	    System.out.print("\\>username:");
	    username = sc.nextLine();
	    System.out.print("\\>password:");
	    password = sc.nextLine();

	    if (UserAPI.verifyUser(username, password)) {
	      System.out.println("Login Successful!");
	    } else {
	      System.out.println("This user does not exist");
	      System.exit(1);
	    }

	    //Login Successful
	    int option = -1;
	    do {
	      System.out.println("\nSelect entity for CRUD operations:");
	      System.out.println("1. Users");
	      System.out.println("2. Team");
	      System.out.println("3. Tasks");
	      System.out.println("4. Exit");
	      option = sc.nextInt();
	      sc.nextLine();

	      try {
	        switch (option) {
	          case 1:
	            {
	              user();
	              break;
	            }
	          case 2:
	            {
	              team();
	              break;
	            }
	          case 3:
	            {
	              task();
	              break;
	            }
	          case 4:
	            break;
	          default:
	            {
	              System.out.println("Invalied option!!");
	            }
	        }
	      } catch (InputMismatchException e) {
	        System.out.println("Oops, an invalid value was enterd :(");
	      }
	    } while (option != 4);

	    System.exit(1);
  }
  
  
  public static void user() {
    Scanner sc = new Scanner(System.in);
    int option = -1;
    do {
      System.out.println("\nSelect operation for user:");
      System.out.println("1. Create new user");
      System.out.println("2. Delete existing user");
      System.out.println("3. Modify existing user");
      System.out.println("4. Search user");
      System.out.println("5. Back");
      option = sc.nextInt();
      sc.nextLine();

      switch (option) {
        case 1:
          {
            //TODO: Create new user
            int user_id;
            String user_name;
            String email;
            String password;
            /*
            System.out.println("\nEnter user_id:");
            user_id = sc.nextInt();
            sc.nextLine();*/

            System.out.println("Enter user name:");
            user_name = sc.nextLine();

            System.out.println("Enter email:");
            email = sc.nextLine();

            System.out.println("Enter password:");
            password = sc.nextLine();

            if (UserAPI.addUser(user_name, email, password)) {
              System.out.println("User added successfully.");
              UserAPI.printAllUsers();
            } else {
              System.out.println("Failed to add user.");
            }

            break;
          }
        case 2:
          {
            //TODO: Delete existing user
            String user_name;
            System.out.println("\nEnter user name:");
            user_name = sc.nextLine();

            if (UserAPI.deleteUserByUserName(user_name)) {
              System.out.println("User deleted successfully. (If existed)");
              UserAPI.printAllUsers();
            } else System.out.println("Failed to delete user.");

            break;
          }
        case 3:
          {
            //TODO: Modify existing user
            String user_name;
            System.out.println("\nEnter user name:");
            user_name = sc.nextLine();

            System.out.println("Select attribute:");
            System.out.println("1. password");
            System.out.println("2. email");
            System.out.println("3. team position");
            int _option = sc.nextInt();
            sc.nextLine();

            String updateString = "";
            switch (_option) {
              case 1:
                {
                  System.out.println("Enter new password:");
                  updateString = sc.nextLine();
                  UserAPI.modifyUser(
                    user_name,
                    updateString,
                    UserAPI.UPDATE_PASSWORD
                  );
                  UserAPI.printAllUsers();
                  break;
                }
              case 2:
                {
                  System.out.println("Enter new email");
                  updateString = sc.nextLine();
                  UserAPI.modifyUser(
                    user_name,
                    updateString,
                    UserAPI.UPDATE_EMAIL
                  );
                  UserAPI.printAllUsers();
                  break;
                }
              case 3:
                {
                  System.out.println("Enter new team positon:");
                  updateString = sc.nextLine();
                  UserAPI.modifyUser(
                    user_name,
                    updateString,
                    UserAPI.UPDATE_TEAM_POSITION
                  );
                  UserAPI.printAllUsers();
                  break;
                }
              default:
                {
                  break;
                }
            }

            break;
          }
        case 4:
          {
            //TODO: Search user
            int _option = -1;
            do {
              System.out.println("\n1. Display all users");
              System.out.println("2. Search by user name");
              System.out.println("3. Back");
              _option = sc.nextInt();
              sc.nextLine();

              switch (_option) {
                case 1:
                  {
                    //TODO: Display all users
                    UserAPI.printAllUsers();
                    break;
                  }
                case 2:
                  {
                    //TODO: Search by user name
                    System.out.println("\nEnter user name:");
                    String temp = sc.nextLine();
                    UserAPI.printUser(temp);
                    break;
                  }
                case 3:
                  break;
                default:
                  {
                    System.out.println("Invalid option!");
                  }
              }
            } while (_option != 3);

            break;
          }
        case 5:
          break;
        default:
          {
            System.out.println("Invalied option!");
          }
      }
    } while (option != 5);
  }

  public static void team() {
    Scanner sc = new Scanner(System.in);
    int option = -1;
    do {
      System.out.println("\nSelect operation for team:");
      System.out.println("1. Create new team");
      System.out.println("2. Delete existing team");
      System.out.println("3. Modify existing team name");
      System.out.println("4. Search team");
      System.out.println("5. Add new user to a team");
      System.out.println("6. Remove a user from a team");
      System.out.println("7. Back");
      option = sc.nextInt();
      sc.nextLine();

      switch (option) {
        case 1:
          {
            System.out.println("\nCreate New Team");
            System.out.println("__________________\n");
            String team_name;
            System.out.println("\nEnter a Team Name:");
            team_name = sc.nextLine();
            if (TeamAPI.addTeam(team_name) == true) {
              TeamAPI.printAllTeams();
              System.out.println("Successfully added " + team_name + "!");
            } else {
              System.out.println("Unable to add team");
            }
            break;
          }
        case 2:
          {
            System.out.println("\nDelete Team");
            System.out.println("__________________\n");

            Integer team_id;
            System.out.println("\nEnter a team_id:");
            team_id = sc.nextInt();
            if (TeamAPI.deleteTeam(team_id) == true) {
              System.out.println(
                "Successfully deleted team with id:" + team_id + "!"
              );
              TeamAPI.printAllTeams();
            } else {
              System.out.println("Error deleting team with id:" + team_id);
            }
            break;
          }
        case 3:
          {
            System.out.println("\nUpdate Team Name");
            System.out.println("__________________\n");
            Integer team_id;
            String newTeamName;
            System.out.println("\nEnter team id:");
            team_id = sc.nextInt();
            sc.nextLine();

            System.out.println("\nPlease enter new team name:");
            newTeamName = sc.nextLine();

            TeamAPI.updateTeamName(team_id, newTeamName);
            break;
          }
        case 4:
          {
            System.out.println("\nSearch Team");
            System.out.println("__________________\n");
            Integer team_id;
            System.out.println("\nEnter a team_id:");
            team_id = sc.nextInt();
            TeamAPI.searchTeam(team_id);
            break;
          }
        case 5:
          {
            //TODO: Remove a user from a team
            break;
          }
        case 6:
          {
            //TODO: Add new user to a team
            break;
          }
        case 7:
          break;
        default:
          {
            System.out.println("Invalid option!");
          }
      }
    } while (option != 7);
  }

  public static void task() {
    Scanner sc = new Scanner(System.in);
    int option = -1;
    do {
      System.out.println("\nSelect operation for task:");
      System.out.println("1. Create new task");
      System.out.println("2. Delete existing task");
      System.out.println("3. Modify existing task");
      System.out.println("4. Search task");
      System.out.println("5. Back");
      option = sc.nextInt();
      sc.nextLine();

      switch (option) {
        case 1:
          {
              System.out.println("Add Task");
              System.out.println("__________________\n");           
              break;
          }
        case 2:
          {
            System.out.println("\nDelete Task");
            System.out.println("__________________\n");
            Integer task_id;
            System.out.println("\nEnter a task_id:");
            task_id = sc.nextInt();
            if (TaskAPI.deleteTask(task_id) == true) {
              TaskAPI.printAllTasks();
              System.out.println(
                "Successfully deleted task with id:" + task_id + "!"
              );
            } else {
              System.out.println("Error deleting task with id:" + task_id);
            }
            break;
          }
        case 3:
          {
            //TODO: Modify existing task
            break;
          }
        case 4:
          {
            System.out.println("\nSearch Task");
            System.out.println("__________________\n");
            Integer task_id;
            System.out.println("\nEnter a task_id:");
            task_id = sc.nextInt();
            TaskAPI.searchTask(task_id);
            break;
          }
        case 5:
          break;
        default:
          {
            System.out.println("Invalid option!");
          }
      }
    } while (option != 5);
  }
}