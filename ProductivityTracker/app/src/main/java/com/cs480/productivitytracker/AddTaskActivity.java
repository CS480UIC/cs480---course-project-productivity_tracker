package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskActivity extends AppCompatActivity {
    EditText taskName, taskDesc, userId, teamId;
    Button addTaskBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getSupportActionBar().hide(); // hide the title bar
        taskName = (EditText) findViewById(R.id.task_name);
        taskDesc = (EditText) findViewById(R.id.task_description);
        userId = (EditText) findViewById(R.id.task_user_id);
        teamId = (EditText) findViewById(R.id.task_team_id);
        addTaskBtn = (Button) findViewById(R.id.add_task_btn);
     }

    public void handleAddTask(View view) {
    }
}