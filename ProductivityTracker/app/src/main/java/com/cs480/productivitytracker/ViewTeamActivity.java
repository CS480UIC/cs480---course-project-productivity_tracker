package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ViewTeamActivity extends AppCompatActivity {
    EditText team_member_new_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_team);
        team_member_new_id = (EditText) findViewById(R.id.team_member_new_id);
    }

    public void handleAddNewTMBtn(View view) {
    }
}