package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.cs480.staticData.UserData;
import com.cs480.threadingConstructs.ConnectionThread;
import com.cs480.threadingConstructs.ConnectionThreadHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewTeamActivity extends AppCompatActivity {
    private static final String TAG = "ViewTeamActivity";

    public static ViewTeamActivity viewTeamActivityInstance;

    EditText team_member_new_id;
    String teamName;
    String teamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_team);
        team_member_new_id = (EditText) findViewById(R.id.team_member_new_id);

        //Get Team Name and ID
        teamName = this.getIntent().getExtras().getString("team_name");
        teamId = this.getIntent().getExtras().getString("team_id");

        ((TextView) findViewById(R.id.teamNameAndId)).setText(teamName + " id:" + teamId);

        viewTeamActivityInstance = this;

        queryForTeamMembers();
    }

    public void queryForTeamMembers()
    {
        Message msg = new Message();
        msg.what = ConnectionThreadHandler.VIEW_TEAM_ACTIVITY_LOAD_TEAM_MEMBERS;

        Bundle bundle = new Bundle();
        bundle.putString("team_id", teamId);
        msg.setData(bundle);

        ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .sendMessage(msg);
    }

    public void loadTeamMembers(JSONArray teamMembers) throws JSONException {
        ArrayList<String> members = new ArrayList<>();
        ListView lv = findViewById(R.id.team_member_lv);
        for(int n = 0; n < teamMembers.length(); n++)
        {
            JSONObject object = teamMembers.getJSONObject(n);
            String userName = object.getString("user_name");
            String userId = object.getString("user_id");
            members.add(userName + " - id:" + userId);
        }

        Log.i(TAG,"Received num of users =  " + members.size());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this, R.layout.simpe_list_item,members);
        lv.setAdapter(arrayAdapter);
    }

    public void handleAddNewTMBtn(View view)
    {
    }

    @Override
    protected void onDestroy() {
        viewTeamActivityInstance = null;
        super.onDestroy();
    }
}