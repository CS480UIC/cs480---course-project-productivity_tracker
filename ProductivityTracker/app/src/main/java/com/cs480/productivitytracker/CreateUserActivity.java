package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
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

    //For username,password and email editTexts
    String userNameValue, passwordValue, emailValue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        //Get Threading constructs
        connectionThread = ConnectionThread.getConnectionThread();


        //TODO: (Jacob) Add Required UI
        //Note: This activity will open once the user clicks on "Create new user" from the login screen.
        //Also



        createUserActivityInstance = this;
    }

    public void handleCreateUser(View view)
    {
        //TODO: (Jacob)Uncomment when GUI elements are added
        //userNameValue = userNameInput.getText().toString();
        //passwordValue = passwordInput.getText().toString();
        //emailValue = emailInput.getText().toString();

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

    public void onQueryResultForCreate(boolean result)
    {
        //TODO: (Jacob)Re route to login activity if user was successfully created
        //Notes:
        //result = true => user was successfully created
        //result = false => user was not created successfully
        //Suggestion => display toast of result and go to login activity regardless of success or failure
    }

    @Override
    protected void onDestroy()
    {
        createUserActivityInstance =  null;
        super.onDestroy();
    }

}