package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.cs480.databaseAPI.taskCrud;
import com.cs480.threadingConstructs.ConnectionThread;
import com.cs480.threadingConstructs.ConnectionThreadHandler;

public class AddTaskActivity extends AppCompatActivity {
    //Logcat TAG
    private static final String TAG = "AddTaskActivity";

    //Activity Instance
    public static AddTaskActivity addTaskActivityInstance;

    //Threading Constructs
    ConnectionThread connectionThread;

    EditText taskName, taskDesc, userId, teamId;
    String taskNameValue, taskDescValue, userIDValue, teamIdValue, priorityValue = "1";
    Button addTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_add_task);

        //Get Threading constructs
        connectionThread = ConnectionThread.getConnectionThread();

        // UI elements
        taskName = (EditText) findViewById(R.id.task_name);
        taskDesc = (EditText) findViewById(R.id.task_description);
        userId = (EditText) findViewById(R.id.task_user_id);
        teamId = (EditText) findViewById(R.id.task_team_id);
        addTaskBtn = (Button) findViewById(R.id.add_task_btn);
        ((RadioButton) findViewById(R.id.radio_priority_1)).setChecked(true);

        addTaskActivityInstance = this;
    }

    public void handleAddTask(View view) {
        //todo (Not too important) Figure out how to remove %20 for any words with spaces

        // Field Values
        taskNameValue = taskName.getText().toString();
        taskDescValue = taskDesc.getText().toString();
        userIDValue = userId.getText().toString();
        teamIdValue = teamId.getText().toString();

        Log.i(TAG, "verify user message sent to connection thread");
        // Todo (Not too important) add current date time
        Message msg = new Message();
        msg.what = ConnectionThreadHandler.ADD_TASK_ACTIVITY_ADD_TASK;

        Bundle bundle = new Bundle();
        bundle.putString("taskName", taskNameValue);
        bundle.putString("taskDesc", taskDescValue);
        bundle.putString("userId", userIDValue);
        bundle.putString("teamId", teamIdValue);
        bundle.putString("priority", priorityValue);
        bundle.putInt("attributeOption", taskCrud.ADD_TASK);
        msg.setData(bundle);

        ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .sendMessage(msg);
    }

    public void onQueryResultForAddTask(boolean result) {
        if (result) {
            // Todo figure out why a toast that says "error logging in" occurs
            Toast.makeText(AddTaskActivity.this, "Add Task successful", Toast.LENGTH_SHORT).show();
            this.finish(); // Go back to dashboard
        } else {
            Toast.makeText(AddTaskActivity.this, "Add Task failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_priority_1:
                if (checked)
                    priorityValue = "1";
                break;

            case R.id.radio_priority_2:
                if (checked)
                    priorityValue = "2";
                break;

            case R.id.radio_priority_3:
                if (checked)
                    priorityValue = "3";
                break;

            case R.id.radio_priority_4:
                if (checked)
                    priorityValue = "4";
                break;

        }


    }


    @Override
    protected void onDestroy() {
        addTaskActivityInstance = null;
        super.onDestroy();
    }

}