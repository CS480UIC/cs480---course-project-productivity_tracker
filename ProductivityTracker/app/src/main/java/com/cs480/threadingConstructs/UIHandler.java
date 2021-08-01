package com.cs480.threadingConstructs;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.cs480.productivitytracker.AddTaskActivity;
import com.cs480.productivitytracker.CreateUserActivity;
import com.cs480.productivitytracker.DashboardActivity;
import com.cs480.productivitytracker.DeleteTaskActivity;
import com.cs480.productivitytracker.LoginActivity;
import com.cs480.productivitytracker.ProfileActivity;
import com.cs480.productivitytracker.TaskActivity;

public class UIHandler
{
    //ui handler instance
    public static Handler uiHandler;

    //TAG for logcat
    private final static String TAG = "UIHandler";

    //Message Codes
    public static final int VERIFY_USER_RESULT = 100;
    public static final int CREATE_USER_RESULT = 150;
    public static final int MODIFY_USER_RESULT = 200;
    public static final int DELETE_USER_RESULT = 250;
    public static final int ADD_TASK_RESULT = 300;
    public static final int DELETE_TASK_RESULT = 350;



    public static void initUIHandler()
    {

        uiHandler = new Handler(Looper.getMainLooper())
        {
            public void handleMessage(Message msg) {
                Log.i(TAG, "New message");
                int what = msg.what;

                switch(what)
                {

                    case CREATE_USER_RESULT:
                    {
                        boolean result  = msg.getData().getBoolean("addUser");
                        Log.i(TAG, "Received add user result " + result);

                        if(CreateUserActivity.createUserActivityInstance != null)
                        {
                            CreateUserActivity.createUserActivityInstance.onQueryResultForCreate(result);
                        }
                        else
                        {
                            Log.i(TAG, "createUserActivityInstance was null");
                        }
                        break;
                    }


                    case ADD_TASK_RESULT:
                    {
                        boolean result  = msg.getData().getBoolean("addTask");
                        Log.i(TAG, "Received add task result");
                        if(AddTaskActivity.addTaskActivityInstance != null){
                            AddTaskActivity.addTaskActivityInstance.onQueryResultForAddTask(result);
                        }
                        else
                        {
                            Log.i(TAG, "addTaskActivityInstance was null");
                        }
                    }
                    case DELETE_TASK_RESULT:
                    {
                        Log.i(TAG, "Delete task result: "+msg.getData() );
                        boolean result  = msg.getData().getBoolean("delete");
                        Log.i(TAG, "Received delete task result");
                        if(DeleteTaskActivity.deleteTaskActivityInstance != null){
                            DeleteTaskActivity.deleteTaskActivityInstance.onQueryResultForDeleteTask(result);
                        }
                        else
                        {
                            Log.i(TAG, "deleteTaskActivityInstance was null");
                        }
                    }

                    case VERIFY_USER_RESULT:
                    {

                        boolean result  = msg.getData().getBoolean("verifyUser");
                        Log.i(TAG, "Received verify user result");

                        if(LoginActivity.loginActivityInstance != null)
                        {
                            LoginActivity.loginActivityInstance.onQueryResultForLogin(result);
                        }
                        else
                        {
                            Log.i(TAG, "loginActivityInstance was null");
                        }

                        break;
                    }

                    case MODIFY_USER_RESULT:
                    {
                        boolean result  = msg.getData().getBoolean("modifyUser");
                        Log.i(TAG, "Received modify user result");

                        if(ProfileActivity.profileActivityInstance != null)
                        {
                            ProfileActivity.profileActivityInstance.onQueryResultForUpdate(result);
                        }
                        else
                        {
                            Log.i(TAG, "profileActivityInstance was null");
                        }

                        break;
                    }

                    case DELETE_USER_RESULT:
                    {

                        boolean result  = msg.getData().getBoolean("deleteUser");
                        Log.i(TAG, "Received delete user result");

                        if(ProfileActivity.profileActivityInstance != null)
                        {
                            ProfileActivity.profileActivityInstance.onQueryResultForDelete(result);
                        }
                        else
                        {
                            Log.i(TAG, "profileActivityInstance was null");
                        }
                        break;
                    }
                }

            }

        };

    }



}




