package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cs480.databaseAPI.userCrud;
import com.cs480.staticData.UserData;
import com.cs480.threadingConstructs.ConnectionThread;
import com.cs480.threadingConstructs.ConnectionThreadHandler;

public class ProfileActivity extends AppCompatActivity {

    TextView profileName;
    Button updateEmailBtn, updateTeamPositionBtn, updatePasswordBtn;
    Button deleteAccountBtn;

    //Threading Constructs
    ConnectionThread connectionThread;

    //Activity Instance
    public static ProfileActivity profileActivityInstance;

    //TODO: Add functionality to update the profile and show usage of CRUD on User entity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide(); // hide the title bar

        //Get Threading constructs
        connectionThread = ConnectionThread.getConnectionThread();

        // Get UI Elements
        updateEmailBtn = (Button) findViewById(R.id.update_email_btn);
        updateTeamPositionBtn = (Button) findViewById(R.id.update_team_position_btn);
        updatePasswordBtn = (Button) findViewById(R.id.update_password_btn);
        deleteAccountBtn = (Button) findViewById(R.id.delete_profile_btn);

        profileActivityInstance  = this;
    }

    public void handleUpdateEmail(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new email");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String update = input.getText().toString();

                //tell connectionThread
                Message msg = new Message();
                msg.what = ConnectionThreadHandler.PROFILE_ACTIVITY_UPDATE_USER_ATTRIBUTES;

                Bundle bundle = new Bundle();
                bundle.putString("update", update);
                bundle.putInt("attributeOption", userCrud.UPDATE_EMAIL);
                msg.setData(bundle);

                ConnectionThread
                        .getConnectionThread()
                        .getConnectionThreadHandler()
                        .sendMessage(msg);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public void handleUpdateTeamPosition(View view)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new team position");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String update = input.getText().toString();

                //message to db
                Message msg = new Message();
                msg.what = ConnectionThreadHandler.PROFILE_ACTIVITY_UPDATE_USER_ATTRIBUTES;

                Bundle bundle = new Bundle();
                bundle.putString("update", update);
                bundle.putInt("attributeOption", userCrud.UPDATE_TEAM_POSITION);
                msg.setData(bundle);

                ConnectionThread
                        .getConnectionThread()
                        .getConnectionThreadHandler()
                        .sendMessage(msg);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void handleUpdatePassword(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new team position");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String update = input.getText().toString();

                //message to db
                Message msg = new Message();
                msg.what = ConnectionThreadHandler.PROFILE_ACTIVITY_UPDATE_USER_ATTRIBUTES;

                Bundle bundle = new Bundle();
                bundle.putString("update", update);
                bundle.putInt("attributeOption", userCrud.UPDATE_PASSWORD);
                msg.setData(bundle);

                ConnectionThread
                        .getConnectionThread()
                        .getConnectionThreadHandler()
                        .sendMessage(msg);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    public void handleDeleteProfile(View view)
    {
        Message msg = new Message();
        msg.what = ConnectionThreadHandler.PROFILE_ACTIVITY_DELETE_USER;

        Bundle bundle = new Bundle();
        bundle.putString("user", UserData.user_name);
        msg.setData(bundle);

        ConnectionThread
                .getConnectionThread()
                .getConnectionThreadHandler()
                .sendMessage(msg);
    }

    public void onQueryResultForUpdate(boolean result)
    {
        if(result)
        {
            Toast.makeText(ProfileActivity.this,"Update successful",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(ProfileActivity.this,"Update failed",Toast.LENGTH_SHORT).show();
        }
    }

    public void onQueryResultForDelete(boolean result)
    {
        if(result)
        {
            UserData.user_name = null;
            UserData.user_id = null;
            UserData.email = null;
            UserData.team_position = null;

            Toast.makeText(ProfileActivity.this,"Delete successful",Toast.LENGTH_SHORT).show();

            Intent mStartActivity = new Intent(ProfileActivity.this, LoginActivity.class);
            int mPendingIntentId = 123456;
            PendingIntent mPendingIntent = PendingIntent.getActivity(ProfileActivity.this, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager mgr = (AlarmManager)ProfileActivity.this.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 10, mPendingIntent);
            finishAffinity();


        }
        else
        {
            Toast.makeText(ProfileActivity.this,"Delete failed",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy()
    {
        profileActivityInstance =  null;
        super.onDestroy();
    }

}