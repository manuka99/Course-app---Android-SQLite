package com.lab.finallab.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.lab.finallab.R;
import com.lab.finallab.database.DBHandler;

public class Message extends AppCompatActivity {

    private static final String TAG = "Message";
    public static final String MESSAGE_ID = "message_id";

    DBHandler dbHandler;
    TextView subject, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);

        dbHandler = new DBHandler(this);

        if (getIntent().hasExtra(MESSAGE_ID)) {
            com.lab.finallab.model.Message message_db = dbHandler.getMessage(getIntent().getStringExtra(MESSAGE_ID));
            if (message_db.get_id() == null) {
                Toast.makeText(this, "Message does not exist", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                subject.setText(message_db.getSubject());
                message.setText(message_db.getMessage());
            }
        } else
            finish();
    }
}