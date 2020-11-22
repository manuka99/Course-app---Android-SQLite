package com.lab.finallab.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lab.finallab.R;
import com.lab.finallab.database.DBHandler;
import com.lab.finallab.model.Message;
import com.lab.finallab.recyclerView.MessagesRecycler;

import java.util.List;

public class Student extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView welcomeText;
    MessagesRecycler messagesRecycler;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        welcomeText = findViewById(R.id.welcomeText);
        recyclerView = findViewById(R.id.recyclerView);

        SharedPreferences sharedPreferences = this.getSharedPreferences("File", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(Login.LOG_USER_NAME, null);

        dbHandler = new DBHandler(this);

        if (name != null)
            welcomeText.setText("Welcome " + name);
        else {
            Toast.makeText(this, "Please Log In!", Toast.LENGTH_LONG).show();
            finish();
        }

        List<Message> messages = dbHandler.getMessages();
        messagesRecycler = new MessagesRecycler(this, messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(messagesRecycler);
    }
}