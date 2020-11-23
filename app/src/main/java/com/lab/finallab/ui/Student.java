package com.lab.finallab.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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
    ListView listView;

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

        //recycler view
        messagesRecycler = new MessagesRecycler(this, messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.setAdapter(messagesRecycler);

        String[] msgArray = new String[messages.size()];
        int i = 0;
        for(Message message: messages){
            msgArray[i] = message.getSubject();
            ++i;
        }

        //list view
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, msgArray);
        listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
    }
}