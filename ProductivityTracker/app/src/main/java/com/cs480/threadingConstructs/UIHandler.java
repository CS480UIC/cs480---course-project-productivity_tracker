package com.cs480.threadingConstructs;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.cs480.productivitytracker.CreateUserActivity;
import com.cs480.productivitytracker.LoginActivity;

public class UIHandler
{
    //ui handler instance
    public static Handler uiHandler;

    //TAG for logcat
    private final static String TAG = "UIHandler";

    //Message Codes
    public static final int VERIFY_USER_RESULT = 100;
    public static final int CREATE_USER_RESULT = 200;

    public static void initUIHandler()
    {

        uiHandler = new Handler(Looper.getMainLooper())
        {
            public void handleMessage(Message msg) {
                Log.i(TAG, "New message");
                int what = msg.what;

                if (what >= 100 && what < 200)
                {
                    handleMessageForLoginActivity(msg);
                }
                else if(what >= 200 && what < 300)
                {
                    handleMessageForCreateUserActivity(msg);
                }

            }

        };

    }


    private static void handleMessageForCreateUserActivity(Message msg)
    {
        int what = msg.what;

        switch(what)
        {

            case CREATE_USER_RESULT:
            {

                boolean result  = msg.getData().getBoolean(" addUser");
                Log.i(TAG, "Received add user result");

                if(CreateUserActivity.createUserActivityInstance != null)
                {
                    CreateUserActivity.createUserActivityInstance.onSuccessfulCreation(result);
                }
                else
                {
                    Log.i(TAG, "createUserActivityInstance was null");
                }

            }
        }

    }


    private static void handleMessageForLoginActivity(Message msg)
    {
        int what = msg.what;

        switch(what)
        {

            case VERIFY_USER_RESULT:
            {

                boolean result  = msg.getData().getBoolean("verifyUser");
                Log.i(TAG, "Received verify user result");

                if(LoginActivity.loginActivityInstance != null)
                {
                    LoginActivity.loginActivityInstance.onSuccessfulLogin(result);
                }
                else
                {
                    Log.i(TAG, "loginActivityInstance was null");
                }

            }
        }

    }

}




