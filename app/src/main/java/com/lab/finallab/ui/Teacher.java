package com.lab.finallab.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lab.finallab.R;
import com.lab.finallab.database.DBHandler;
import com.lab.finallab.model.Message;

public class Teacher extends AppCompatActivity {

    TextView welcomeText;
    Button send;
    EditText subject, message;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        welcomeText = findViewById(R.id.welcomeText);
        send = findViewById(R.id.send);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);

        dbHandler = new DBHandler(this);

        SharedPreferences sharedPreferences = this.getSharedPreferences("File", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(Login.LOG_USER_NAME, null);
        final String user_id = sharedPreferences.getString(Login.LOG_USER_ID, null);

        if (name != null)
            welcomeText.setText("Welcome " + name);
        else {
            Toast.makeText(this, "Please Log In!", Toast.LENGTH_LONG).show();
            finish();
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sub = subject.getText().toString().trim();
                String msg = message.getText().toString().trim();
                if (!sub.isEmpty() && !msg.isEmpty()) {
                    boolean re = dbHandler.addMessage(new Message(user_id, sub, msg));
                    if (re)
                        Toast.makeText(getApplicationContext(), "All details were added", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Details were not saved", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getApplicationContext(), "Enter all details", Toast.LENGTH_LONG).show();
            }
        });

    }
}