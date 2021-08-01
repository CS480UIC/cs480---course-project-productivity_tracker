package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cs480.threadingConstructs.ConnectionThread;
import com.cs480.threadingConstructs.ConnectionThreadHandler;

public class CreateUserActivity extends AppCompatActivity {

    //Logcat TAG
    private static final String TAG = "Create User Activity";

    //Activity Instance: Used to access methods from UIHandler
    public static CreateUserActivity createUserActivityInstance;

    //Threading Constructs
    ConnectionThread connectionThread;

    EditText userNameInput, passwordInput, emailInput;

    //For username,password and email editTexts
    String userNameValue, passwordValue, emailValue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        getSupportActionBar().hide(); // hide the title bar

        //Get Threading constructs
        connectionThread = ConnectionThread.getConnectionThread();

        userNameInput = (EditText) findViewById(R.id.new_user_name);
        passwordInput = (EditText) findViewById(R.id.new_user_password);
        emailInput = (EditText) findViewById(R.id.new_user_email);

        createUserActivityInstance = this;
    }

    public void handleCreateUser(View view)
    {
        userNameValue = userNameInput.getText().toString();
        passwordValue = passwordInput.getText().toString();
        emailValue = emailInput.getText().toString();

        //TODO: (Yash) test working after addition of GUI
        //Send message to connection thread
        Log.i(TAG, "create user message sent to connection thread");
        Bundle bundle = new Bundle();
        bundle.putString("user", userNameValue);
        bundle.putString("password", passwordValue);
        bundle.putString("email", emailValue);

        Message msgToConnectionThread = new Message();
        msgToConnectionThread.what = ConnectionThreadHandler.CREATE_USER_ACTIVITY_CREATE_USER;
        msgToConnectionThread.setData(bundle);

        connectionThread
                .getConnectionThreadHandler()
                .sendMessage(msgToConnectionThread);
    }

    // todo figure out why onQueryResultForCreate is not being called
    public void onQueryResultForCreate(boolean result)
    {
        if(result)
        {
            Toast.makeText(CreateUserActivity.this,"Create new user successful",Toast.LENGTH_SHORT).show();
            this.finish(); // Go back to dashboard
        }
        else
        {
            Toast.makeText(CreateUserActivity.this,"Create new user failed",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy()
    {
        createUserActivityInstance =  null;
        super.onDestroy();
    }

}