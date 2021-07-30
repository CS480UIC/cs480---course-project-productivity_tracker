package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs480.databaseAPI.*;
import com.cs480.threadingConstructs.ConnectionThread;
import com.cs480.threadingConstructs.ConnectionThreadHandler;
import com.cs480.threadingConstructs.UIHandler;


public class LoginActivity extends AppCompatActivity
{
    //Logcat TAG
    private static final String TAG = "Login Activity";

    //Activity Instance
    public static LoginActivity loginActivityInstance;

    //Threading Constructs
    ConnectionThread connectionThread;

    String userNameValue, passwordValue;
    EditText userNameInput,passwordInput;
    Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.login_activity);

        //Threading instances
        UIHandler.initUIHandler();
        connectionThread = ConnectionThread.getConnectionThread();
        connectionThread.setuiHandler(UIHandler.uiHandler);
        connectionThread.start();

        // Form UI
        userNameInput = (EditText) findViewById(R.id.username_input);
        passwordInput = (EditText) findViewById(R.id.password_input);
        signInBtn = (Button) findViewById(R.id.sign_in_btn);
        //TODO: Add button to create user

        //Activity Instance: Used to access methods from UIHandler
        loginActivityInstance = this;
    }
    public void handleSignIn(View view) {
        // Grab field values
        userNameValue = userNameInput.getText().toString();
        passwordValue = passwordInput.getText().toString();


        //Send message to connection thread
        Log.i(TAG, "verify user message sent to connection thread");
        Bundle bundle = new Bundle();
        bundle.putString("user", userNameValue);
        bundle.putString("password", passwordValue);

        Message msgToConnectionThread = new Message();
        msgToConnectionThread.what = ConnectionThreadHandler.LOGIN_ACTIVITY_VERIFY_USER;
        msgToConnectionThread.setData(bundle);

        connectionThread
                .getConnectionThreadHandler()
                .sendMessage(msgToConnectionThread);
    }

    public void onSuccessfulLogin(boolean result)
    {
        /*
        result = true => Login successful
        result = false => Login unsuccessful
         */

        //Update GUI accroding to result
        if (result)
        {
            Log.i(TAG, "Login Successful");

            //make toast to indicate success
            Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();

            //start dashboard activity
            Intent startDashboard = new Intent(LoginActivity.this,DashboardActivity.class );
            startDashboard.putExtra("put","username and password here"); //Optional parameters
            LoginActivity.this.startActivity(startDashboard);
        }
        else
        {
            Log.i(TAG, "Error Logging in");

            //make toast to indicate failure
            Toast.makeText(LoginActivity.loginActivityInstance,"Error logging in",Toast.LENGTH_SHORT).show();
        }
    }

    public void handleCreateNewUser(View view)
    {
        //TODO: (Jacob) Start CreateUserActivity on Button press
    }

    @Override
    protected void onDestroy()
    {
        loginActivityInstance =  null;
        super.onDestroy();
    }
}