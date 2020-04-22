package com.example.scienceguider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Add_new_Quiz extends AppCompatActivity {

    EditText add_question;
    EditText add_answer;
    Button save;
    Spinner subject;
    Spinner topic;

//    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new__quiz);
    }
}
