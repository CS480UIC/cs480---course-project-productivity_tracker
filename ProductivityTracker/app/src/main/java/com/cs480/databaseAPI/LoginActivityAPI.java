package com.cs480.databaseAPI;

import android.content.Intent;
import android.util.Log;

import com.cs480.threadingConstructs.ConnectionThread;
import com.cs480.threadingConstructs.ConnectionThreadHandler;

public class LoginActivityAPI
{
    public static final String TAG = "LoginActivityAPI";

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

}
