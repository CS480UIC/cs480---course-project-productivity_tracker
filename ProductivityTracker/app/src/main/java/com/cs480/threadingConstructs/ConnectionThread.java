package com.cs480.threadingConstructs;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.cs480.config;

public class ConnectionThread extends Thread
{


    //TAG for Logcat
    private String TAG = "ConnectionThread";

    //Singleton
    private static ConnectionThread connectionThread;

    //uiHandler
    private Handler uiHandler;

    //handler of this thread
    private static ConnectionThreadHandler connectionThreadHandler;

    //IP
    public static String IP = config.IP;

    //Port
    public static int PORT = config.PORT;

    private ConnectionThread()
    {
        //Private constructor for singleton
    }

    public static ConnectionThread getConnectionThread()
    {
        if(connectionThread == null)
        {
            connectionThread = new ConnectionThread();
        }
        return connectionThread;
    }

    public void setuiHandler(Handler uiHandler)
    {
        this.uiHandler = uiHandler;
    }

    public ConnectionThreadHandler getConnectionThreadHandler()
    {
        return connectionThreadHandler;
    }

    @Override
    public void run()
    {
        //Prepare the looper
        if (Looper.myLooper() == null)
        {
            Looper.prepare();
        }
        Log.i(TAG, "Looper.prepare() ran successfully");

        //Create Handler
        connectionThreadHandler = new ConnectionThreadHandler(uiHandler);

        //Start listening to messages
        Looper.loop();

        //Kill thread
        Log.i(TAG, "exit run()");
    }
}
