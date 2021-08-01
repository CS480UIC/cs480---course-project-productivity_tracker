package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cs480.staticData.UserData;
import com.cs480.threadingConstructs.ConnectionThread;

public class DashboardActivity extends AppCompatActivity
{
    //Logged in user
    private String currentUser;

    //Activity Instance
    public static DashboardActivity dashboardActivityInstance;

    //Threading Constructs
    ConnectionThread connectionThread;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_dashboard);

        //Get Threading constructs
        connectionThread = ConnectionThread.getConnectionThread();

        //Populate Profile Details
        loadProfileDetails();

        //User task list
        loadUserTasks();

        dashboardActivityInstance = this;
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

    public void handleViewTeamBtn(View view) {
        Intent startViewTeam = new Intent(DashboardActivity.this,ViewTeamActivity.class );
        startViewTeam.putExtra("",""); //Optional parameters
        DashboardActivity.this.startActivity(startViewTeam);
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

    public void loadUserTasks(){

    }

    @Override
    protected void onResume()
    {
        loadProfileDetails();
        super.onResume();
    }

    public void handleComplexQueriesBtn(View view) {
        Intent startComplexQueryActivity = new Intent(DashboardActivity.this,ComplexQueryActivity.class );
        DashboardActivity.this.startActivity(startComplexQueryActivity);
    }
}
