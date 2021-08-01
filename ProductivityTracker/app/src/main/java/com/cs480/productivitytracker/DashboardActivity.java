package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.cs480.databaseAPI.userCrud;
import com.cs480.staticData.UserData;
import com.cs480.threadingConstructs.ConnectionThread;
import com.cs480.threadingConstructs.ConnectionThreadHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity
{

    //TAG for Logcat
    private String TAG = "Dashboard Activity";

    //Logged in user
    private String currentUser;

    //Activity Instance
    public static DashboardActivity dashboardActivityInstance;

    //Threading Constructs
    ConnectionThread connectionThread;

    ListView teamListView;
    ListView taskListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_dashboard);


        //Get Threading constructs
        connectionThread = ConnectionThread.getConnectionThread();

        //GUI Elements
        teamListView = findViewById(R.id.team_lv);
        taskListView = findViewById(R.id.task_lv);
        setListeners();

        //Populate Profile Details
        loadProfileDetails();

        //User task list
        queryForLoadUserTasks();
        queryForLoadUserTeams();

        dashboardActivityInstance = this;
    }

    public void setListeners()
    {
        teamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView item = (TextView) view;

                String teamName = ""+((String)item.getText()).split(" \\(")[0];
                String teamId = ""+((String)item.getText()).split("id: ")[1].charAt(0);

                Log.i(TAG, "Clicked on: " + teamName + " " + teamId);

                Intent startViewTeam = new Intent(DashboardActivity.this,ViewTeamActivity.class );
                startViewTeam.putExtra("team_name",teamName); //Optional parameters
                startViewTeam.putExtra("team_id",teamId);
                DashboardActivity.this.startActivity(startViewTeam);

            }
        });

        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String taskName  = ((String)((TextView) view).getText()).split(" - ")[0];
                String taskId = "";
                for(int n = 0; n < UserData.user_tasks.length(); n++)
                {
                    try {
                        JSONObject object = UserData.user_tasks.getJSONObject(n);
                        if(object.getString("task_name").equals(taskName))
                            taskId =  object.getString("task_id");

                    } catch (JSONException e) {
                        continue;
                    }
                }

                Log.i(TAG,"Clicked on =  " + taskName + ", " + taskId);

                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
                builder.setTitle("Mark as complete?");


                String finalTaskId = taskId;
                builder.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //message to db
                        Message msg = new Message();
                        msg.what = ConnectionThreadHandler.DASHBOARD_ACTIVITY_MARK_TASK_CHECKED;

                        Bundle bundle = new Bundle();
                        bundle.putString("task_id", finalTaskId);
                        msg.setData(bundle);

                        ConnectionThread
                                .getConnectionThread()
                                .getConnectionThreadHandler()
                                .sendMessage(msg);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });

    }

    public void handleEditProfileBtn(View view) {
        Intent startProfile = new Intent(DashboardActivity.this,ProfileActivity.class );
        startProfile.putExtra("",""); //Optional parameters
        DashboardActivity.this.startActivity(startProfile);

        //Note: After Editing repopulate list view of profile details


    }

    public void handleAddTaskBtn(View view) {
        Intent startAddTask = new Intent(DashboardActivity.this,AddTaskActivity.class );
        startAddTask.putExtra("",""); //Optional parameters
        DashboardActivity.this.startActivity(startAddTask);
    }

    public void handleDeleteTaskBtn(View view) {
        Intent startDeleteTask = new Intent(DashboardActivity.this,DeleteTaskActivity.class );
        startDeleteTask.putExtra("",""); //Optional parameters
        DashboardActivity.this.startActivity(startDeleteTask);
    }

    public void loadProfileDetails()
    {
        String[] userDetailsStringArray = {
                "User Name: " + UserData.user_name,
                "User ID: " + UserData.user_id,
                "Email: " + UserData.email,
                "Team Position: " + UserData.team_position
        };

        ((TextView)findViewById(R.id.user_name_text_view)).setText(userDetailsStringArray[0]);
        ((TextView)findViewById(R.id.user_id_text_view)).setText(userDetailsStringArray[1]);
        ((TextView)findViewById(R.id.user_email_text_view)).setText(userDetailsStringArray[2]);
        ((TextView)findViewById(R.id.user_team_position_text_view)).setText(userDetailsStringArray[3]);
    }

    public void queryForLoadUserTasks()
    {
        Message msg = new Message();
        msg.what = ConnectionThreadHandler.DASHBOARD_ACTIVITY_LOAD_USER_TASKS;

        Bundle bundle = new Bundle();
        bundle.putString("user_id", UserData.user_id);
        msg.setData(bundle);

        ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .sendMessage(msg);

    }

    public void queryForLoadUserTeams()
    {
        Message msg = new Message();
        msg.what = ConnectionThreadHandler.DASHBOARD_ACTIVITY_LOAD_USER_TEAMS;

        Bundle bundle = new Bundle();
        bundle.putString("user_id", UserData.user_id);
        msg.setData(bundle);

        ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .sendMessage(msg);

    }

    public void loadUserTasks(JSONArray ja) throws JSONException
    {
        ArrayList<String> taskNames = new ArrayList<>();
        taskListView = findViewById(R.id.task_lv);
        for(int n = 0; n < ja.length(); n++)
        {
            JSONObject object = ja.getJSONObject(n);
            String taskName = object.getString("task_name");
            String taskPriority = object.getString("task_priority");
            taskNames.add(taskName + " - P(" + taskPriority + ")");
        }

        Log.i(TAG,"Received num of tasks =  " + taskNames.size());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this, R.layout.simpe_list_item,taskNames);
        taskListView.setAdapter(arrayAdapter);
    }

    public void loadUserTeams(JSONArray ja) throws JSONException
    {
        ArrayList<String> teamNames = new ArrayList<>();
        teamListView = findViewById(R.id.team_lv);
        for(int n = 0; n < ja.length(); n++)
        {
            JSONObject object = ja.getJSONObject(n);
            String teamName = object.getString("team_name");
            String teamId = object.getString("team_id");
            teamNames.add(teamName + " (id: "+teamId+ ")");
        }

        Log.i(TAG,"Received num of teams =  " + teamNames.size());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this, R.layout.simpe_list_item,teamNames);
        teamListView.setAdapter(arrayAdapter);
    }

    public void onMarkedTaskAsComplete(Boolean result)
    {
        queryForLoadUserTasks();
    }

    @Override
    protected void onResume()
    {
        loadProfileDetails();
        queryForLoadUserTasks();
        queryForLoadUserTeams();
        super.onResume();
    }

    public void handleComplexQueriesBtn(View view) {
        Intent startComplexQueryActivity = new Intent(DashboardActivity.this,ComplexQueryActivity.class );
        DashboardActivity.this.startActivity(startComplexQueryActivity);
    }
}
