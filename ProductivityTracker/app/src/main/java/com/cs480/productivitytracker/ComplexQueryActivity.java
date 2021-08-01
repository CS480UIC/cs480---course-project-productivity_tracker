package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cs480.staticData.UserData;
import com.cs480.threadingConstructs.ConnectionThread;
import com.cs480.threadingConstructs.ConnectionThreadHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ComplexQueryActivity extends AppCompatActivity {

    TextView resultHeader;
    EditText team_id_input;
    String team_id_value;


    //TAG for Logcat
    private String TAG = "Complex Queries Activity";

    public static ComplexQueryActivity complexQueryActivityInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_query);
        getSupportActionBar().hide(); // hide the title bar
        team_id_input = (EditText) findViewById(R.id.complex_team_id_input);
        resultHeader = (TextView) findViewById(R.id.resultHeader);

        complexQueryActivityInstance = this;

    }

    public void handleGetTeamTestCaseTasks(View view) {
        Log.i(TAG, "Get Teams Test Cases button pressed...");
        resultHeader.setText("Test Cases Tasks");
        queryForTestCaseTasks();
    }

    public void queryForTestCaseTasks() {
        // Get value form input field
        team_id_value = team_id_input.getText().toString();

        Message msg = new Message();
        msg.what = ConnectionThreadHandler.COMPLEX_ACTIVITY_TEST_CASE_TEAM_TASKS;

        Bundle bundle = new Bundle();
        bundle.putString("team_id", team_id_value);
        msg.setData(bundle);

        ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .sendMessage(msg);
    }

    public void loadTeamTestCaseTasks(JSONArray ja) throws JSONException {
        ArrayList<String> taskNames = new ArrayList<>();
        ListView lv = findViewById(R.id.complex_queries_lv);

        for (int n = 0; n < ja.length(); n++) {
            JSONObject object = ja.getJSONObject(n);
            String taskName = object.getString("task_name");
            String userName = object.getString("user_name");
            String category = object.getString("category");
            taskNames.add(taskName + " - (" + userName + ") - CATEGORY: " + category);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.simpe_list_item, taskNames);
        lv.setAdapter(arrayAdapter);
    }


    // ----- Team Sorted Tasks ------
    public void handleGetTeamsSortedTask(View view) {
        Log.i(TAG, "Get teams sorted tasks button pressed...");
        queryForTeamSortedTasks();
        resultHeader.setText("Sorted Team Tasks");
    }

    public void queryForTeamSortedTasks() {
        // Get value form input field
        team_id_value = team_id_input.getText().toString();

        Message msg = new Message();
        msg.what = ConnectionThreadHandler.COMPLEX_ACTIVITY_LOAD_SORTED_TEAM_TASKS;

        Bundle bundle = new Bundle();
        bundle.putString("team_id", team_id_value);
        msg.setData(bundle);

        ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .sendMessage(msg);
    }

    public void loadTeamSortedTasks(JSONArray ja) throws JSONException {
        ArrayList<String> taskNames = new ArrayList<>();
        ListView lv = findViewById(R.id.complex_queries_lv);

        for (int n = 0; n < ja.length(); n++) {
            JSONObject object = ja.getJSONObject(n);
            String taskName = object.getString("task_name");
            String userName = object.getString("user_name");
            String dueDate = object.getString("dead_line_date");
            taskNames.add(taskName + " - (" + userName + ") - DUE:" + dueDate);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.simpe_list_item, taskNames);
        lv.setAdapter(arrayAdapter);
    }


    // -- SWE Tasks ------
    public void handleGetTeamsSoftwareEngineeringTasks(View view) {
        Log.i(TAG, "Get Teams SWE Tasks button pressed...");
        queryForSoftwareEngineeringTasks();
        resultHeader.setText("SWE Tasks");
    }

    public void queryForSoftwareEngineeringTasks() {
        // Get value form input field
        team_id_value = team_id_input.getText().toString();

        Message msg = new Message();
        msg.what = ConnectionThreadHandler.COMPLEX_ACTIVITY_LOAD_SWE_TASKS;

        Bundle bundle = new Bundle();
        bundle.putString("team_id", team_id_value);
        msg.setData(bundle);

        ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .sendMessage(msg);
    }

    public void loadSoftwareEngineeringTasks(JSONArray ja) throws JSONException {
        ArrayList<String> taskNames = new ArrayList<>();
        ListView lv = findViewById(R.id.complex_queries_lv);

        for (int n = 0; n < ja.length(); n++) {
            JSONObject object = ja.getJSONObject(n);
            String taskName = object.getString("task_name");
            String userName = object.getString("user_name");
            taskNames.add(taskName + " - (" + userName + ")");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.simpe_list_item, taskNames);
        lv.setAdapter(arrayAdapter);
    }


//   ---------- PM Email Query ----------

    public void handleGetTeamsProjectManagerEmail(View view) {
        Log.i(TAG, "Get Teams PM Email button pressed...");
        resultHeader.setText("Project Managers Email");
        queryForLoadProjectManagersEmail();
    }

    public void loadProjectManagersEmail(JSONArray ja) throws JSONException {
        ArrayList<String> emails = new ArrayList<>();

        ListView lv = findViewById(R.id.complex_queries_lv);
        for (int i = 0; i < ja.length(); i++) {
            JSONObject object = ja.getJSONObject(i);
            String pmEmail = object.getString("email");
            emails.add(pmEmail);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.simpe_list_item, emails);
        lv.setAdapter(arrayAdapter);
    }

    public void queryForLoadProjectManagersEmail() {
        // Get value form input field
        team_id_value = team_id_input.getText().toString();

        Message msg = new Message();
        msg.what = ConnectionThreadHandler.COMPLEX_ACTIVITY_LOAD_PM_EMAILS;
        Bundle bundle = new Bundle();
        bundle.putString("team_id", team_id_value);

        msg.setData(bundle);

        ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .sendMessage(msg);
    }
}