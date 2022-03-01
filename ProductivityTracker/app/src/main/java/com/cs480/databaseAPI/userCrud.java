package com.cs480.databaseAPI;

import android.util.Log;

import com.cs480.threadingConstructs.ConnectionThread;

import org.json.JSONArray;

public class userCrud
{
    public static final int UPDATE_PASSWORD = 100;
    public static final int UPDATE_TEAM_POSITION = 101;
    public static final int UPDATE_EMAIL = 102;
    public static final String TAG = "UserCRUD";

    public static Boolean verifyUser(String user, String password)
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/verifyUser?" +
                user + "&"+
                password;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler().httpRequest(request);

        Log.i(TAG, "verifyUser result = " + response);

        if( response == null || response.equals("false"))
        {
            return false;
        }
        else return true;
    }

    //TODO: (YASH) Test addUser
    public static Boolean addUser(String user, String password, String email)
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/addUser?" +
                user + "&"+
                password+ "&"+
                email;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler().httpRequest(request);

        Log.i(TAG, "add user result = " + response);

        if( response == null || response.equals("false"))
        {
            return false;
        }
        else return true;
    }

    //TODO: (YASH) Test deleteUser
    public static Boolean deleteUser(String user)
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/deleteUser?" +
                user;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler().httpRequest(request);

        Log.i(TAG, "deleteUser result = " + response);

        if( response == null || response.equals("false"))
        {
            return false;
        }
        else return true;
    }

    //TODO: (YASH) Test getUsers
    public static String getUser(String user)
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/getUser?" +
                user;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler().httpRequest(request);

        Log.i(TAG, "getUser result = " + response);

        return response;
    }

    //TODO: (YASH) Test getAllUsers
    public static String getAllUsers()
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/getAllUsers?" +
                "none";

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler().httpRequest(request);

        Log.i(TAG, "getAllUsers result = " + response);

        return response;
    }

    //TODO: (YASH) Test ModifyUser
    public static Boolean modifyUser(String user, String param, int option)
    {

        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/modifyUser?" +
                  user + "&"+
                  param+ "&"+
                  Integer.toString(option);

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler().httpRequest(request);

        Log.i(TAG, "modifyUser result = " + response);

        if( response == null || response.equals("false"))
        {
            return false;
        }
        else return true;
    }

}
