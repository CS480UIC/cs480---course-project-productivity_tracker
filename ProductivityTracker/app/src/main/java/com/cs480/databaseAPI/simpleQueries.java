package com.cs480.databaseAPI;

import android.util.Log;

import com.cs480.threadingConstructs.ConnectionThread;

public class simpleQueries
{
    public static final String TAG = "simpleQueries";


    // Produces list of teams a user is a part of
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

    // Produces list of users on a certain team
    public static String getListOfMembersInTeam(String team_id)
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/simpleGetListOfUsersInTeam?" +
                team_id;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .httpRequest(request);

        Log.i(TAG, "getListOfMembersInTeam = " + response);

        return response;
    }

    public static String markTaskAsCompleted(String task_id)
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/simpleMarkTaskAsComplete?" +
                task_id;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .httpRequest(request);

        Log.i(TAG, "markTaskAsCompleted = " + response);

        return response;
    }


}
