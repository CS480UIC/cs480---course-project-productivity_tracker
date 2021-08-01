package com.cs480.threadingConstructs;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.cs480.productivitytracker.AddTaskActivity;
import com.cs480.productivitytracker.ComplexQueryActivity;
import com.cs480.productivitytracker.CreateUserActivity;
import com.cs480.productivitytracker.DashboardActivity;
import com.cs480.productivitytracker.DeleteTaskActivity;
import com.cs480.productivitytracker.LoginActivity;
import com.cs480.productivitytracker.ProfileActivity;
import com.cs480.productivitytracker.TaskActivity;
import com.cs480.productivitytracker.ViewTeamActivity;
import com.cs480.staticData.UserData;

import org.json.JSONArray;
import org.json.JSONException;

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
    public static final int DASH_BOARD_LOAD_USER_TASKS_GUI = 400;
    public static final int DASH_BOARD_LOAD_USER_TEAMS_GUI = 450;
    public static final int VIEW_TEAM_LOAD_TEAM_MEMBERS_GUI = 500;
    public static final int COMPLEX_LOAD_PM_EMAILS_GUI = 550;
    public static final int COMPLEX_ACTIVITY_LOAD_SWE_TASKS_GUI = 600;
    public static final int COMPLEX_ACTIVITY_LOAD_SORTED_TEAM_TASKS_GUI = 650;



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
                        break;
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
                        break;
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

                    case DASH_BOARD_LOAD_USER_TASKS_GUI:
                    {
                        JSONArray ja = null;
                        try {
                            ja = new JSONArray(msg.getData().getString("complexGetUserSorted"));

                            if(DashboardActivity.dashboardActivityInstance != null)
                            {
                                UserData.user_tasks = ja;
                                DashboardActivity.dashboardActivityInstance.loadUserTasks(ja);
                            }
                            else
                            {
                                Log.i(TAG, "dashboardActivityInstance was null");
                            }
                        } catch (JSONException e) {
                            Log.i(TAG, "dashboard could not load JSONArray for tasks");
                        }


                        break;
                    }

                    case DASH_BOARD_LOAD_USER_TEAMS_GUI:
                    {
                        JSONArray ja = null;
                        try {
                            ja = new JSONArray(msg.getData().getString("getUserTeams"));

                            if(DashboardActivity.dashboardActivityInstance != null)
                            {
                                DashboardActivity.dashboardActivityInstance.loadUserTeams(ja);
                            }
                            else
                            {
                                Log.i(TAG, "dashboardActivityInstance was null");
                            }
                        } catch (JSONException e) {
                            Log.i(TAG, "dashboard could not load JSONArray for teams");
                        }


                        break;
                    }

                    case COMPLEX_ACTIVITY_LOAD_SWE_TASKS_GUI: {
                        JSONArray ja = null;
                        try{
                            ja = new JSONArray(msg.getData().getString("complexGetSWE")); // Key is url function

                            if(ComplexQueryActivity.complexQueryActivityInstance != null) {
                                UserData.user_teams = ja;
                                ComplexQueryActivity.complexQueryActivityInstance.loadSoftwareEngineeringTasks(ja);
                            }
                            else {
                                Log.i(TAG, "dashboardActivityInstance was null");
                            }

                        }catch (JSONException e){
                            Log.i(TAG, "dashboard could not load JSONArray for teams | "+e.getMessage());
                        }
                        break;
                    }
                    case COMPLEX_LOAD_PM_EMAILS_GUI: {
                        JSONArray ja = null;
                        try {
                            ja = new JSONArray(msg.getData().getString("complexLoadPmEmails"));

                            if (ComplexQueryActivity.complexQueryActivityInstance != null) {
                                UserData.user_teams = ja;
                                ComplexQueryActivity.complexQueryActivityInstance.loadProjectManagersEmail(ja);
                            } else {
                                Log.i(TAG, "complexQueryActivityInstance was null");
                            }
                        } catch (JSONException e) {
                            Log.i(TAG, "complex activity could not load JSONArray for PM Emails");
                        }
                        break;
                    }
                    case COMPLEX_ACTIVITY_LOAD_SORTED_TEAM_TASKS_GUI: {
                        JSONArray ja = null;
                        try {
                            ja = new JSONArray(msg.getData().getString("complexGetTeamsSorted"));

                            if (ComplexQueryActivity.complexQueryActivityInstance != null) {
                                ComplexQueryActivity.complexQueryActivityInstance.loadTeamSortedTasks(ja);
                            } else {
                                Log.i(TAG, "complexQueryActivityInstance was null");
                            }
                        } catch (JSONException e) {
                            Log.i(TAG, "complex activity could not load JSONArray for complexGetTeamsSorted");
                        }
                        break;
                    }


                    case VIEW_TEAM_LOAD_TEAM_MEMBERS_GUI:
                    {
                        JSONArray ja = null;
                        try {
                            ja = new JSONArray(msg.getData().getString("getListOfMembersInTeam"));

                            if(ViewTeamActivity.viewTeamActivityInstance != null)
                            {
                                UserData.user_teams = ja;
                                ViewTeamActivity.viewTeamActivityInstance.loadTeamMembers(ja);
                            }
                            else
                            {
                                Log.i(TAG, "viewTeamActivityInstance was null");
                            }
                        } catch (JSONException e) {
                            Log.i(TAG, "viewTeamActivity could not load JSONArray for teams members");
                        }
                        break;
                    }
                }
            }

        };

    }
}




