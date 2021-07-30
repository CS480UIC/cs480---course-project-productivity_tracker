package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;
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


public class LoginActivity extends AppCompatActivity
{
    //Logcat TAG
    private static final String TAG = "Login Activity";

    //Message Codes
    public static final int VERIFY_USER_RESULT = 100;

    //Threading Constructs
    Handler uiHandler;
    ConnectionThread connectionThread;

    String userNameValue, passwordValue;
    EditText userNameInput,passwordInput;
    Button signInBtn;


    public static String MySqlDatabase = "ProductivityTracker";
    public static String MySqlUser = "root";
    public static String MySqlPassword = "capnsdelight12";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.login_activity);

        //Create threading constructs
        initUIHandler();
        connectionThread = ConnectionThread.getConnectionThread();
        connectionThread.setuiHandler(uiHandler);
        connectionThread.start();

        // Form UI
        userNameInput = (EditText) findViewById(R.id.username_input);
        passwordInput = (EditText) findViewById(R.id.password_input);
        signInBtn = (Button) findViewById(R.id.sign_in_btn);
    }
    public void handleSignIn(View view) {
        // Grab field values
        userNameValue = userNameInput.getText().toString();
        passwordValue = passwordInput.getText().toString();

        /*
        if (UserAPI.verifyUser(userNameValue, passwordValue)) {
            Log.i("Login Activity", "Inputs: "+userNameValue+" | "+passwordValue);
        } else {
            Log.i("Login Activity", "Error");
        }
        */

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

    public void onSuccessfulLogin()
    {
        Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
        //TODO: Go to dashboard activity
    }


    //Thread stuff
    private void initUIHandler()
    {
        this.uiHandler = new Handler(Looper.getMainLooper())
        {
            public void handleMessage(Message msg)
            {
                Log.i(TAG, "New message");
                int what = msg.what;

                switch(what)
                {
                    case VERIFY_USER_RESULT:
                    {

                        boolean result  = msg.getData().getBoolean("verifyUser");
                        Log.i(TAG, "Handler: Received verify user result");

                        //Update GUI accroding to result
                        if (result)
                        {
                            Log.i(TAG, "Handler: Login Successful");
                            onSuccessfulLogin();
                        }
                        else
                        {
                            Log.i(TAG, "Handler: Error Logging in");
                            Toast.makeText(LoginActivity.this,"Error logging in",Toast.LENGTH_SHORT).show();
                        }



                    }
                }
            }

        };
    }

}