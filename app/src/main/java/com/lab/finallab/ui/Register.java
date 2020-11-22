package com.lab.finallab.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.lab.finallab.R;
import com.lab.finallab.database.DBHandler;
import com.lab.finallab.database.UserMaster;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button register;
    EditText name, password;
    CheckBox check_teacher, check_student;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.register);
        name = findViewById(R.id.name);
        password = findViewById(R.id.pass);
        check_teacher = findViewById(R.id.check_teacher);
        check_student = findViewById(R.id.check_student);

        register.setOnClickListener(this);
        check_teacher.setOnClickListener(this);
        check_student.setOnClickListener(this);

        dbHandler = new DBHandler(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        check_teacher.setChecked(false);
        check_student.setChecked(false);
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            int view_id = view.getId();
            switch (view_id) {
                case R.id.check_teacher:
                    if (check_teacher.isChecked())
                        check_student.setChecked(false);
                    break;
                case R.id.check_student:
                    if (check_student.isChecked())
                        check_teacher.setChecked(false);
                    break;
                case R.id.register:
                    funRegister();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view_id);
            }
        }
    }

    private void funRegister() {
        String username = name.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String type = UserMaster.User.TYPE_TEACHER;

        if (username == null || pass == null)
            Toast.makeText(getApplicationContext(), "Please enter name and password", Toast.LENGTH_LONG).show();
        else if (!check_teacher.isChecked() && !check_student.isChecked())
            Toast.makeText(getApplicationContext(), "Please select type", Toast.LENGTH_LONG).show();
        else {
            if (check_student.isChecked())
                type = UserMaster.User.TYPE_STUDENT;

            boolean re = dbHandler.register(username, pass, type);

            if (re)
                Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Not registered", Toast.LENGTH_LONG).show();
        }
    }

}