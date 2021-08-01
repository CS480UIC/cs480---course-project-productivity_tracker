package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cs480.staticData.UserData;
import com.cs480.threadingConstructs.ConnectionThread;
import com.cs480.threadingConstructs.ConnectionThreadHandler;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DeleteTaskActivity extends AppCompatActivity {
    //Logcat TAG
    private static final String TAG = "DeleteTaskActivity";

    EditText deleteTaskId;
    Button deleteTaskButton;
    String deleteTaskIdValue;

    ArrayList<Pair<String, String>> tasks;

    String selectedTaskId;

    public static DeleteTaskActivity deleteTaskActivityInstance;

    //Threading Constructs
    ConnectionThread connectionThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_delete_task);

        //Get Threading constructs
        connectionThread = ConnectionThread.getConnectionThread();

        //Load tasks from userData
        try {
            tasks = UserData.getTasksNameAndIDs();
        } catch (JSONException e) {
            Toast.makeText(DeleteTaskActivity.this,"Failed to load tasks",Toast.LENGTH_SHORT).show();
            tasks =  new ArrayList<>();
        }

        deleteTaskId = (EditText) findViewById(R.id.delete_task_id);
        deleteTaskButton = (Button) findViewById(R.id.delete_task_btn);

        initSpinner();

        deleteTaskActivityInstance = this;
    }

    public void handleDeleteTaskBtn(View view)
    {
        if(selectedTaskId == null)
        {
            Toast.makeText(DeleteTaskActivity.this,"Please select task",Toast.LENGTH_SHORT).show();
            return;
        }

        // Field Values
        deleteTaskIdValue = selectedTaskId;

        Log.i(TAG, "verify user message sent to connection thread");
        Message msg = new Message();
        msg.what = ConnectionThreadHandler.DELETE_TASK_ACTIVITY_DELETE_TASK;

        Bundle bundle = new Bundle();
        bundle.putString("deleteTaskIdValue", deleteTaskIdValue);
        msg.setData(bundle);

        ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .sendMessage(msg);
    }

    public void onQueryResultForDeleteTask(boolean result)
    {
        if(result)
        {
            // Todo figure out why a toast that says "error logging in" occurs
            Toast.makeText(DeleteTaskActivity.this,"Delete Task successful",Toast.LENGTH_SHORT).show();
            this.finish(); // Go back to dashboard
        }
        else
        {
            Toast.makeText(DeleteTaskActivity.this,"Delete Task failed",Toast.LENGTH_SHORT).show();
        }
    }

    public void initSpinner()
    {
        Spinner taskSpinner = (Spinner)findViewById(R.id.task_spinner);
        List<String> taskNames = new ArrayList<String>();

        for(Pair<String, String> x : tasks)
        {
            taskNames.add(x.second);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, taskNames);
        taskSpinner.setAdapter(dataAdapter);

        taskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedTaskId = tasks.get(position).first;
                deleteTaskId.setText(selectedTaskId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedTaskId = null;

            }
        });

    }



    @Override
    protected void onDestroy()
    {
        deleteTaskActivityInstance =  null;
        super.onDestroy();
    }
}