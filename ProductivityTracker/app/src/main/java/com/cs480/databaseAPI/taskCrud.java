package com.cs480.databaseAPI;

import android.util.Log;

import com.cs480.threadingConstructs.ConnectionThread;

public class taskCrud {
    public static final String TAG = "TaskCRUD";
    public static final int ADD_TASK = 200;

    public static Boolean addTask(String taskName, String taskDesc, String userId, String teamId){

        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/addTask?" +
                taskName + "&"+
                taskDesc+ "&"+
                userId+"&"+
                teamId;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler().httpRequest(request);

        Log.i(TAG, "add task result = " + response);

        if( response == null || response.equals("false"))
        {
            return false;
        }
        else return true;
    }

    public static Boolean deleteTask(String taskID){

        String request = "http://" + ConnectionThread.IP + ":" + Integer.toString(ConnectionThread.PORT) +
                "/deleteTask?" +
                taskID;

        String response = ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler().httpRequest(request);

        if( response == null || response.equals("false"))
        {
            return false;
        }
        else return true;
    }

}
