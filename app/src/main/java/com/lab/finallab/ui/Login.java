package com.lab.finallab.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lab.finallab.R;
import com.lab.finallab.database.DBHandler;
import com.lab.finallab.database.UserMaster;
import com.lab.finallab.model.Users;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button login, register;
    EditText name, password;

    DBHandler dbHandler;

    private static final String TAG = "Login";
    public static final String LOG_USER_NAME = "user_name";
    public static final String LOG_USER_TYPE = "user_type";
    public static final String LOG_USER_ID = "user_id";
    public static final String LOG_USER_EXPIRE_TIME = "user_expire_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        name = findViewById(R.id.name);
        password = findViewById(R.id.pass);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        dbHandler = new DBHandler(this);
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            int view_id = view.getId();
            switch (view_id) {
                case R.id.login:
                    funLogin();
                    break;
                case R.id.register:
                    funRegister();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view_id);
            }
        }
    }

    private void funLogin() {

        long time_day_ms = 24 * 60 * 60 * 1000;
        Date date = new Date();

        String username = name.getText().toString().trim();
        String pass = password.getText().toString().trim();

        Users user = dbHandler.login(username, pass);
        String type = user.getType();

        if(type != null) {
            SharedPreferences sharedPreferences = this.getSharedPreferences("File", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(LOG_USER_NAME, "Manuka")
                    .putString(LOG_USER_ID, user.get_id())
                    .putString(LOG_USER_TYPE, user.getType())
                    .putLong(LOG_USER_EXPIRE_TIME, date.getTime() + time_day_ms)
                    .apply();

            if(type.equals(UserMaster.User.TYPE_STUDENT))
            startActivity(new Intent(getApplicationContext(), Student.class));

            else if(type.equals(UserMaster.User.TYPE_TEACHER))
                    startActivity(new Intent(getApplicationContext(), Teacher.class));

            else
                Toast.makeText(getApplicationContext(), "Account does not has a type", Toast.LENGTH_LONG).show();
        }

        else
            Toast.makeText(getApplicationContext(), "Account not registered", Toast.LENGTH_LONG).show();

    }

    private void funRegister() {
        startActivity(new Intent(getApplicationContext(), Register.class));
    }

}