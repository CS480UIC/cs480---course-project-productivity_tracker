
package com.cs480.productivitytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.cs480.databaseAPI.*;


public class LoginActivity extends AppCompatActivity {

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

        // Form UI
        userNameInput = (EditText) findViewById(R.id.username_input);
        passwordInput = (EditText) findViewById(R.id.password_input);
        signInBtn = (Button) findViewById(R.id.sign_in_btn);
    }
    public void handleSignIn(View view) {
        // Grab field values
        userNameValue = userNameInput.getText().toString();
        passwordValue = passwordInput.getText().toString();

        Intent startDashboard = new Intent(LoginActivity.this,DashboardActivity.class );
        startDashboard.putExtra("put","username and password here"); //Optional parameters
        LoginActivity.this.startActivity(startDashboard);

//        if (UserAPI.verifyUser(userNameValue, passwordValue)) {
//            // todo GO TO DASHBOARD ACTIVITY IF SUCCESSFULL
//            Log.i("Login Activity", "Inputs: "+userNameValue+" | "+passwordValue);
//        } else {
//            Log.i("Login Activity", "Error");
//        }
    }
}