package com.cs480.staticData;

import android.util.Log;
import android.util.Pair;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cs480.productivitytracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserData
{
    public static String user_name;
    public static String email;
    public static String team_position;
    public static String user_id;

    public static JSONArray user_tasks;

    public static JSONArray user_teams;

    public static ArrayList<Pair<String, String>> getTasksNameAndIDs() throws JSONException {

        ArrayList<Pair<String, String>> arr = new ArrayList<>();
        for(int n = 0; n < user_tasks.length(); n++)
        {
            JSONObject object = user_tasks.getJSONObject(n);
            String taskName = object.getString("task_name");
            String taskId =  object.getString("task_id");
            arr.add(new Pair<String, String>(taskId, taskName));
        }
        return arr;
    }

}
