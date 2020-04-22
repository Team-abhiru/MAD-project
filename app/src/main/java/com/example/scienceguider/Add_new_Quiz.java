package com.example.scienceguider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Add_new_Quiz extends AppCompatActivity {

    DatabaseReference refDB;

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

        refDB = FirebaseDatabase.getInstance().getReference();

        add_question = (EditText) findViewById(R.id.id_write_question);
        add_answer = (EditText) findViewById(R.id.id_add_answer);
        save = (Button) findViewById(R.id.id_btn_save);
        subject = (Spinner) findViewById(R.id.spinner);
        topic = (Spinner) findViewById(R.id.spinner2);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = add_question.getText().toString();
                String answer = add_answer.getText().toString();

                String selected_subject = subject.getSelectedItem().toString();
                String selected_topic = topic.getSelectedItem().toString();
            }
        });
    }
}
