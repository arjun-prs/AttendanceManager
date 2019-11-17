package com.example.attendancemanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    EditText password;
    Spinner roll_no;
    Button login;
    private DatabaseReference mDatabase;
    DataSnapshot dataSnapshot;
    DocumentSnapshot document;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Student newStudent = new Student();
    String[] roll_no_list= new String[1008];
    String roll_no_str = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll_no=findViewById(R.id.edSPRollNo);
        password=findViewById(R.id.edETPassword);
        login=findViewById(R.id.edButLogin);
        login.setOnClickListener(this);
        roll_no_list[0]="ADMIN";
        for(int i=1;i<10;i++)
        {
            roll_no_list[i]="CB.EN.U4CSE1740"+Integer.toString(i);
        }
        for(int i=10;i<71;i++)
        {
            roll_no_list[i]="CB.EN.U4CSE174"+Integer.toString(i);
        }
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, roll_no_list);
        roll_no.setAdapter(ad);
        roll_no.setOnItemSelectedListener(this);
    }
    // Read from the database

// ...



    @Override
    public void onClick(View v) {
        //final String roll_no_str = roll_no.getText().toString();
        final String password_str = password.getText().toString();
        DocumentReference docRef = db.collection("student").document(roll_no_str);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NotNull Task<DocumentSnapshot> task)
            {
                document = task.getResult();
                String roll = "DEFAULT";
                String pass = "ABCXYZ";
                int att = 0;
                //if(document!=null)
                {
                     roll = document.get("roll_no").toString();
                     pass = document.get("password").toString();
                     att = (Integer.valueOf(document.get("attendance").toString())) ;
                }
                newStudent.setRoll_no(roll);
                newStudent.setPassword(pass);
                newStudent.setAttendance(att);
            }

        });
        if(newStudent.getRoll_no().equals("ADMIN")&&newStudent.getPassword().equals(password_str))
        {
            showMessage("SUCCESS",newStudent.getRoll_no());
            Intent intent = new Intent(this, admin.class);
            intent.putExtra("doc", newStudent.getRoll_no());
            intent.putExtra("roll", roll_no_list);
            startActivity(intent);
        }
        else if(newStudent.getPassword().equals(password_str))
        {
            showMessage("SUCCESS",newStudent.getRoll_no());
            Intent intent = new Intent(this, second.class);
            intent.putExtra("doc", newStudent.getRoll_no());
            startActivity(intent);
        }

    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CheckedTextView ct = (CheckedTextView)view;
        roll_no_str=ct.getText().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
