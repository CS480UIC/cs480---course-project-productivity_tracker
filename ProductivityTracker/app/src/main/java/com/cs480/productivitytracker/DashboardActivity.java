package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_dashboard);
    }

    public void handleEditProfileBtn(View view) {
        Intent startProfile = new Intent(DashboardActivity.this,ProfileActivity.class );
        startProfile.putExtra("",""); //Optional parameters
        DashboardActivity.this.startActivity(startProfile);
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
}
