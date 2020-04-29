package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.time.Instant;


public class Edit_Material_Details extends AppCompatActivity {

    private Spinner subjectSp;
    private TextView tpName,edit_notification;
    private String oldSubject, key, oldUrl, topic, subject, url;
    private Button update, cancel,browse;
    private Uri pdfUri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__material__details);

        subject = (Spinner) findViewById(R.id.spinner_subject);
        tpName = findViewById(R.id.tp_name);

        ArrayAdapter<String> myAdapter = new ArrayAdapter(Edit_Material_Details.this,
                android.R.layout.simple_list_item_1,getResources()
                .getStringArray(R.array.subjects));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subject.setAdapter(myAdapter);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("topName");
        tpName.setText(name);
        sub = intent.getStringExtra("subject");
    }
}
