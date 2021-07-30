package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteTaskActivity extends AppCompatActivity {

    EditText deleteTaskId;
    Button deleteTaskButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_delete_task);

        deleteTaskId = (EditText) findViewById(R.id.delete_task_id);
        deleteTaskButton = (Button) findViewById(R.id.delete_task_btn);
    }

    public void handleDeleteTaskBtn(View view) {
        Toast.makeText(DeleteTaskActivity.this, "Task Deleted!",
                Toast.LENGTH_LONG).show();    }
}