package com.example.attendancemanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class admin extends AppCompatActivity implements View.OnClickListener {
    TextView roll_no;
    Button present, absent;
    int i=1;
    String[] roll_no_list = new String[1008];
    DocumentSnapshot document;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    int att[] = new int[70];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Bundle b = getIntent().getExtras();
        String newdoc = b.getString("doc");
        showMessage("WELCOME", newdoc);
        roll_no_list = b.getStringArray("roll");
        roll_no=findViewById(R.id.edTVRollNo);
        present=findViewById(R.id.edButPresent);
        absent=findViewById(R.id.edButAbsent);
        present.setOnClickListener(this);
        absent.setOnClickListener(this);
        roll_no.setText(roll_no_list[i]);
        for(int j=0;j<70;j++)
            att[j]=0;
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public void onClick(View v) {
        if(v == present)
        {
            if(i>4)
            {
                showMessage("ERROR", "LIST FINISHED!!!");
            }
            else
            {
                Toast.makeText(getApplicationContext(),"prev "+att[i]+ "i  "+ i, Toast.LENGTH_LONG).show();
                DocumentReference docRef = db.collection("student").document(roll_no_list[i]);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NotNull Task<DocumentSnapshot> task) {
                        document = task.getResult();
                        att[i] = (Integer.valueOf(document.get("attendance").toString()));
                        att[i]++;
                        db.collection("student").document(roll_no_list[i]).update("attendance",att[i]);
                        //Toast.makeText(getApplicationContext(),"after "+att[i]+ "i  "+ i, Toast.LENGTH_LONG).show();
                    }
                });
                Toast.makeText(this,"PRESENT   "+roll_no_list[i],Toast.LENGTH_LONG).show();
                i++;
                roll_no.setText(roll_no_list[i]);
            }

        }
        else if(v == absent)
        {
            if(i>4)
            {
                showMessage("ERROR", "LIST FINISHED!!!");
            }
            else
            {
                i++;
                roll_no.setText(roll_no_list[i]);
                Toast.makeText(this,"ABSENT   "+roll_no_list[i-1],Toast.LENGTH_LONG).show();
            }

        }

    }
}
