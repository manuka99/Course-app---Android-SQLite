package com.lab.finallab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.lab.finallab.ui.Login;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String CHANNEL_ID = "121236b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, Login.class));

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //create chanel
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "chanel_name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("description_default");

            //register chanel
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}