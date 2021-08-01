package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs480.threadingConstructs.ConnectionThread;
import com.cs480.threadingConstructs.ConnectionThreadHandler;

public class DeleteTaskActivity extends AppCompatActivity {
    //Logcat TAG
    private static final String TAG = "DeleteTaskActivity";

    EditText deleteTaskId;
    Button deleteTaskButton;
    String deleteTaskIdValue;

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

        deleteTaskId = (EditText) findViewById(R.id.delete_task_id);
        deleteTaskButton = (Button) findViewById(R.id.delete_task_btn);

        deleteTaskActivityInstance = this;
    }

    public void handleDeleteTaskBtn(View view) {
        // Field Values
        deleteTaskIdValue = deleteTaskId.getText().toString();

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


    @Override
    protected void onDestroy()
    {
        deleteTaskActivityInstance =  null;
        super.onDestroy();
    }
}