package com.example.scienceguider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Add_new_Quiz extends AppCompatActivity {

    EditText add_question;
    EditText add_answer;
    Button save;
    Spinner subject;
    Spinner topic;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new__quiz);

        firestore = FirebaseFirestore.getInstance();

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

                if(question.isEmpty() || answer.isEmpty() || selected_subject.isEmpty() || selected_topic.isEmpty()){

                    Toast.makeText(Add_new_Quiz.this,"Fields are empty",Toast.LENGTH_LONG).show();
                }
                else{

                    DocumentReference reference = firestore.collection("questions").document();
                    Map<String,Object> ques = new HashMap<>();

                    ques.put("question", question);
                    ques.put("option1", answer);
                    ques.put("subject",selected_subject);
                    ques.put("topic", selected_topic);

                    reference.set(ques).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Add_new_Quiz.this,"New Question added",Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Add_new_Quiz.this,"Error on add new question",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
}
