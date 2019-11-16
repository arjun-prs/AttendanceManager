package com.example.attendancemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText roll_no, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll_no=findViewById(R.id.edETRollNo);
        password=findViewById(R.id.edETPassword);
        login=findViewById(R.id.edButLogin);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
