package com.cs480.threadingConstructs;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.cs480.databaseAPI.complexQueries;
import com.cs480.databaseAPI.simpleQueries;
import com.cs480.databaseAPI.userCrud;
import com.cs480.databaseAPI.taskCrud;
import com.cs480.staticData.UserData;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionThreadHandler extends Handler
{


    //TAG for Logcat
    private String TAG = "ConnectionThreadHandler";

    //UI Handler
    private Handler uiHandler;

    //HTTP Request params
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    //Message Codes
    public final static int LOGIN_ACTIVITY_VERIFY_USER = 100;
    public final static int CREATE_USER_ACTIVITY_CREATE_USER = 110;
    public final static int LOAD_USER_DATA_INTO_USERDATA_CLASS = 120;
    public final static int PROFILE_ACTIVITY_UPDATE_USER_ATTRIBUTES = 130;
    public final static int PROFILE_ACTIVITY_DELETE_USER = 140;
    public final static int ADD_TASK_ACTIVITY_ADD_TASK = 150;
    public final static int DELETE_TASK_ACTIVITY_DELETE_TASK = 160;
    public static final int DASHBOARD_ACTIVITY_LOAD_USER_TASKS = 170;
    public static final int DASHBOARD_ACTIVITY_LOAD_USER_TEAMS = 180;
    public static final int COMPLEX_ACTIVITY_LOAD_PM_EMAILS = 190;



    ConnectionThreadHandler(Handler uiHandler)
    {
        this.uiHandler = uiHandler;
    }

    //Handles messages posted to message queue
    public void handleMessage(Message msg)
    {
        int what = msg.what;

        switch(what)
        {

            case ADD_TASK_ACTIVITY_ADD_TASK:
            {
                String taskName = (String) msg.getData().get("taskName");
                String taskDesc = (String) msg.getData().get("taskDesc");
                String userId = (String) msg.getData().get("userId");
                String teamId = (String) msg.getData().get("teamId");
                String priority = (String) msg.getData().get("priority");

                Boolean result = taskCrud.addTask(taskName, taskDesc, userId, teamId, priority);

                Bundle bundle = new Bundle();
                bundle.putBoolean("addTask",result);

                Message replyMsg = new Message();
                replyMsg.what = UIHandler.ADD_TASK_RESULT;
                replyMsg.setData(bundle);

                Log.i(TAG, "Sending add task result");

                uiHandler.sendMessage(replyMsg);

                break;
            }

            case DELETE_TASK_ACTIVITY_DELETE_TASK:
            {
                String taskId = (String) msg.getData().get("deleteTaskIdValue");

                Boolean result = taskCrud.deleteTask(taskId);

                Bundle bundle = new Bundle();
                bundle.putBoolean("delete",result);

                Message replyMsg = new Message();
                replyMsg.what = UIHandler.DELETE_TASK_RESULT;
                replyMsg.setData(bundle);

                Log.i(TAG, "Sending delete task result");

                uiHandler.sendMessage(replyMsg);

                break;
            }

            case LOGIN_ACTIVITY_VERIFY_USER:
            {
                String user = (String) msg.getData().get("user");
                String password = (String) msg.getData().get("password");

                //get result from db
                Boolean result = userCrud.verifyUser(user, password);

                //put result in bundle
                Bundle bundle = new Bundle();
                bundle.putBoolean("verifyUser", result);

                //create message with reply and bundle
                Message replyMsg = new Message();
                replyMsg.what = UIHandler.VERIFY_USER_RESULT;
                replyMsg.setData(bundle);

                Log.i(TAG, "Sending verify user result");
                //send message to ui thread
                uiHandler.sendMessage(replyMsg);

                break;
            }

            case CREATE_USER_ACTIVITY_CREATE_USER:
            {
                String user = (String) msg.getData().get("user");
                String password = (String) msg.getData().get("password");
                String email = (String) msg.getData().get("email");

                //get result from db
                Boolean result = userCrud.addUser(user, password, email);

                //put result in bundle
                Bundle bundle = new Bundle();
                bundle.putBoolean("addUser", result);

                //create message with reply and bundle
                Message replyMsg = new Message();
                replyMsg.what = UIHandler.CREATE_USER_RESULT;
                replyMsg.setData(bundle);

                Log.i(TAG, "Sending add user result = " + result);
                //send message to ui thread
                uiHandler.sendMessage(replyMsg);

                break;
            }

            case LOAD_USER_DATA_INTO_USERDATA_CLASS:
            {
                String user = (String) msg.getData().get("user");

                String result = userCrud.getUser(user);

                try
                {
                   JSONObject jo = new JSONObject(result.trim());

                   UserData.user_name = user;

                   try{UserData.email = jo.getString("email");}catch(Exception e){UserData.email = "NA";};

                   try{UserData.team_position = jo.getString("team_position");}catch(Exception e){UserData.team_position = "NA";};

                   try{UserData.user_id = jo.getString("user_id");}catch(Exception e){UserData.user_id = "NA";};


                }
                catch(Exception e)
                {
                    Log.i(TAG, "Could not convert result to JSON for userData class");
                    UserData.email = "NA";
                    UserData.team_position = "NA";
                    UserData.user_id = "NA";
                }

                Log.i(TAG, "userData(" + UserData.user_id + ", " + UserData.user_name + ", " + UserData.email + ", " + UserData.team_position + ")");

                break;
            }

            case PROFILE_ACTIVITY_UPDATE_USER_ATTRIBUTES:
            {

                String updateAttribute = msg.getData().getString("update");
                int whichAttribute = msg.getData().getInt("attributeOption");

                Boolean result = userCrud.modifyUser(UserData.user_name, updateAttribute, whichAttribute);

                if(result)
                {
                    switch(whichAttribute)
                    {
                        case userCrud.UPDATE_EMAIL:
                        {
                            UserData.email = updateAttribute;
                            break;
                        }
                        case userCrud.UPDATE_TEAM_POSITION:
                        {
                            UserData.team_position = updateAttribute;
                            break;
                        }
                    }
                }


                //put result in bundle
                Bundle bundle = new Bundle();
                bundle.putBoolean("modifyUser", result);

                //create message with reply and bundle
                Message replyMsg = new Message();
                replyMsg.what = UIHandler.MODIFY_USER_RESULT;
                replyMsg.setData(bundle);

                Log.i(TAG, "Sending modify user result");
                //send message to ui thread
                uiHandler.sendMessage(replyMsg);


                break;
            }

            case PROFILE_ACTIVITY_DELETE_USER:
            {
                String user = (String) msg.getData().get("user");

                //get result from db
                Boolean result = userCrud.deleteUser(user);

                //put result in bundle
                Bundle bundle = new Bundle();
                bundle.putBoolean("deleteUser", result);

                //create message with reply and bundle
                Message replyMsg = new Message();
                replyMsg.what = UIHandler.DELETE_USER_RESULT;
                replyMsg.setData(bundle);

                Log.i(TAG, "Sending delete user result");
                //send message to ui thread
                uiHandler.sendMessage(replyMsg);

                break;
            }

//            Complex Queries
            case COMPLEX_ACTIVITY_LOAD_PM_EMAILS: {
                String team_id = (String) msg.getData().get("team_id");
                String result = complexQueries.getTeamsProjectManagerEmail(team_id);
                Bundle bundle = new Bundle();
                bundle.putString("complexLoadPmEmails",result);

                Message replyMsg = new Message();
                replyMsg.what = UIHandler.COMPLEX_LOAD_PM_EMAILS_GUI;
                replyMsg.setData(bundle);
                Log.i(TAG, "Sending PM Email to complex");
                uiHandler.sendMessage(replyMsg);
                break;
            }

            case DASHBOARD_ACTIVITY_LOAD_USER_TASKS:
            {
                String user_id = (String) msg.getData().get("user_id");

                String result = complexQueries.complexGetUserSorted(user_id);

                Bundle bundle = new Bundle();
                bundle.putString("complexGetUserSorted",result);

                Message replyMsg = new Message();
                replyMsg.what = UIHandler.DASH_BOARD_LOAD_USER_TASKS_GUI;
                replyMsg.setData(bundle);

                Log.i(TAG, "Sending user tasks to dashboard");

                uiHandler.sendMessage(replyMsg);

                break;
            }

            case DASHBOARD_ACTIVITY_LOAD_USER_TEAMS:
            {
                String user_id = (String) msg.getData().get("user_id");

                String result = simpleQueries.getUserTeams(user_id);

                Bundle bundle = new Bundle();
                bundle.putString("getUserTeams",result);

                Message replyMsg = new Message();
                replyMsg.what = UIHandler.DASH_BOARD_LOAD_USER_TEAMS_GUI;
                replyMsg.setData(bundle);

                Log.i(TAG, "Sending user teams to dashboard");

                uiHandler.sendMessage(replyMsg);

                break;
            }
        }


    }

    public String httpRequest(String sUrl){

        String inputLine;
        String result;

        try {

            Log.i("cmnd", sUrl);
            URL myUrl = new URL(sUrl);
            HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            connection.connect();

            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            StringBuilder stringBuilder = new StringBuilder();

            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            reader.close();
            streamReader.close();

            result = stringBuilder.toString();

        }

        catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }

}
