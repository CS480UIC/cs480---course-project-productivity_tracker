package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView profileName, profileUsername, profileEmail, profilePosition;
    Button deleteAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileName = (TextView) findViewById(R.id.profile_name_label);
        profileUsername = (TextView) findViewById(R.id.profile_username_label);
        profileEmail = (TextView) findViewById(R.id.profile_email_label);
        profilePosition = (TextView) findViewById(R.id.profile_position_label);
        deleteAccountBtn = (Button) findViewById(R.id.delete_profile_btn);
    }

    public void handleDeleteProfile(View view) {
    }
}