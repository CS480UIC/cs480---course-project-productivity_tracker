package com.cs480.databaseAPI;

import android.util.Log;

import com.cs480.threadingConstructs.ConnectionThread;

public class simpleQueries
{
    public static final String TAG = "simpleQueries";

    //Complex Query 1 via HTTP
    // Produces tasks with the category of "test cases" given a team_id
    public static String getUserTeams(String user_id)
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/simpleGetUserTeams?" +
                user_id;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .httpRequest(request);

        Log.i(TAG, "getUserTeams result = " + response);

        return response;
    }


}
