package com.cs480.threadingConstructs;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.cs480.databaseAPI.LoginActivityAPI;
import com.cs480.productivitytracker.LoginActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionThreadHandler extends Handler
{
    //TAG for Logcat
    private String TAG = "ConnectionThreadHandler";

    //UI Handler
    private Handler uiHandler;

    //HTTP Request params
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    //Message Codes
    public final static int LOGIN_ACTIVITY_VERIFY_USER = 100;



    ConnectionThreadHandler(Handler uiHandler)
    {
        this.uiHandler = uiHandler;
    }

    //Handles messages posted to message queue
    public void handleMessage(Message msg)
    {
        int what = msg.what;

        switch(what)
        {
            case LOGIN_ACTIVITY_VERIFY_USER:
            {
                String user = (String) msg.getData().get("user");
                String password = (String) msg.getData().get("password");

                //get result from db
                Boolean result = LoginActivityAPI.verifyUser(user, password);

                //put result in bundle
                Bundle bundle = new Bundle();
                bundle.putBoolean("verifyUser", result);

                //create message with reply and bundle
                Message replyMsg = new Message();
                replyMsg.what = LoginActivity.VERIFY_USER_RESULT;
                replyMsg.setData(bundle);

                Log.i(TAG, "Sending verify user result");
                //send message to ui thread
                uiHandler.sendMessage(replyMsg);
            }
        }


    }

    public String httpRequest(String sUrl){

        String inputLine;
        String result;

        try {

            Log.i("cmnd", sUrl);
            URL myUrl = new URL(sUrl);
            HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            connection.connect();

            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            StringBuilder stringBuilder = new StringBuilder();

            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            reader.close();
            streamReader.close();

            result = stringBuilder.toString();

        }

        catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }

}
