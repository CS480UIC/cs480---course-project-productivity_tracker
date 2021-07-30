package com.cs480.databaseAPI;

import android.util.Log;

import com.cs480.threadingConstructs.ConnectionThread;

public class CreateUserActivityAPI
{
    public static final String TAG = "LoginActivityAPI";

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
}
