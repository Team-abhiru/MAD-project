package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

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

    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list_user_view);

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

        firestore = FirebaseFirestore.getInstance();
    }
}
