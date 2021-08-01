package com.cs480.databaseAPI;

import android.util.Log;

import com.cs480.threadingConstructs.ConnectionThread;

public class complexQueries
{
    public static final String TAG = "complexQueries";

    //Complex Query 1 via HTTP
    // Produces tasks with the category of "test cases" given a team_id
    public static String getTeamTestCaseTask(String team_id)
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/complexGetTestCases?" +
                team_id;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .httpRequest(request);

        Log.i(TAG, "getTeamTestCaseTask result = " + response);

        return response;
    }

    // Complex Query 2 via HTTP
    // Produces highest priority tasks given a user_id
    public static String getUsersHighestPriorityTasks(String user_id)
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/complexGetHPTasks?" +
                user_id;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .httpRequest(request);

        Log.i(TAG, "getUsersHighestPriorityTasks result = " + response);

        return response;
    }

    // Complex Query 3 via HTTP
    // Produces a team's tasks where the uses position is Software Engineering
    public static String getTeamsSoftwareEngineeringTasks(String team_id)
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/complexGetSWETasks?" +
                team_id;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .httpRequest(request);

        Log.i(TAG, "getTeamsSoftwareEngineeringTasks result = " + response);

        return response;
    }

    // Complex Query 4 via HTTP
    // Produces the email of the project manager given a team_id
    public static String getTeamsProjectManagerEmail(String team_id)
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/complexGetPMEmail?" +
                team_id;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .httpRequest(request);

        Log.i(TAG, "getTeamsProjectManagerEmail result = " + response);

        return response;
    }


    // Complex Query 5 via HTTP
    // Produces all the tasks of a given team that are incomplete and order by high priority
    public static String getTeamsSortedTask(String team_id)
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/complexGetTeamsSortedTasks?" +
                team_id;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .httpRequest(request);

        Log.i(TAG, "getTeamsSortedTask result = " + response);

        return response;
    }

    // Complex Query 6 via HTTP
    // Produces all the tasks of a given user that are incomplete and order by high priority
    public static String complexGetUserSorted(String user_id)
    {
        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/complexGetUserSorted?" +
                user_id;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .httpRequest(request);

        Log.i(TAG, "complexGetUserSorted result = " + response);

        return response;
    }


}
