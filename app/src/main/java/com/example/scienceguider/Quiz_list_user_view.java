package com.example.scienceguider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Quiz_list_user_view extends AppCompatActivity {

    private TextView question;
    private TextView marks;
    private TextView subject;
    private TextView topic;
    private TextView counter;
    private RadioGroup radioGroup;
    private RadioButton option1, option2, option3;
    private Button confirm;

    private int all_questions;
    private int complete_questions;
    private int score;
    private int countdown;

    private List<Question> questionList;
    DatabaseReference refDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list_user_view);

        refDB = FirebaseDatabase.getInstance().getReference("Questions");

        question = (TextView) findViewById(R.id.id_question);
        marks = (TextView) findViewById(R.id.id_score);
        subject = (TextView) findViewById(R.id.id_subject);
        topic = (TextView) findViewById(R.id.id_subject_topic);
        counter = (TextView) findViewById(R.id.id_counter);

        radioGroup = (RadioGroup) findViewById(R.id.id_radio_group);
        option1 = (RadioButton) findViewById(R.id.id_answer1);
        option2 = (RadioButton) findViewById(R.id.id_answer2);
        option3 = (RadioButton) findViewById(R.id.id_answer3);

        confirm = (Button) findViewById(R.id.id_btn_confirm);

        refDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Question question = null;
                question = dataSnapshot.getValue(Question.class);

                question.setQuestion(question.getQuestion());
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    System.out.println(question.getQuestion());
                    System.out.println(question.getOption1());
                    System.out.println(question.getOption3());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error");
            }
        });
    }
}
