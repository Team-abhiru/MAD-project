package com.example.scienceguider;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Quiz_list_user_view extends AppCompatActivity {

    private TextView question;
    private TextView marks;
    private TextView subject;
    private TextView quesNumber;
    private TextView counter;
    private RadioGroup radioGroup;
    private RadioButton option1, option2, option3;
    private Button confirm;

    private int questionsCount = 0;
    private int questionNum = 0;
    private int score;
    private int countdown;

    private ColorStateList defaultColor;
    private Question currQues;
    private Question questionArray = null;
    private Boolean answered;
    private List<Question> questionList;
    private DatabaseReference refDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list_user_view);

        Log.i("State","Create");

        refDB = FirebaseDatabase.getInstance().getReference("Questions");

        questionList = new ArrayList<>();

        question = (TextView) findViewById(R.id.id_question);
        marks = (TextView) findViewById(R.id.id_score);
        subject = (TextView) findViewById(R.id.id_subject);
        quesNumber = (TextView) findViewById(R.id.id_question_number);
        counter = (TextView) findViewById(R.id.id_counter);

        radioGroup = (RadioGroup) findViewById(R.id.id_radio_group);
        option1 = (RadioButton) findViewById(R.id.id_answer1);
        option2 = (RadioButton) findViewById(R.id.id_answer2);
        option3 = (RadioButton) findViewById(R.id.id_answer3);

        confirm = (Button) findViewById(R.id.id_btn_confirm);

        defaultColor = option1.getTextColors();

        refDB.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("GetValues", "Get values to an array");
                questionList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    questionArray = snapshot.getValue(Question.class);
                    questionList.add(questionArray);
                }
                questionsCount = questionList.size();
                getQuestion();

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!answered){
                            if(option1.isChecked() || option2.isChecked() || option3.isChecked()){
                                checkAnswer();
                            }else{
                                Toast.makeText(Quiz_list_user_view.this,"Select a answer",Toast.LENGTH_SHORT);
                            }

                        }else{
                            getQuestion();

                        }
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("State","Start");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("State","Resume");
        question.setText("Hello");
    }

    private void getQuestion(){
        Log.i("State","question");
        System.out.println(questionsCount);
        if(questionNum < questionsCount){
            option1.setTextColor(defaultColor);
            option2.setTextColor(defaultColor);
            option3.setTextColor(defaultColor);

            radioGroup.clearCheck();

            currQues = questionList.get(questionNum);

            question.setText(currQues.getQuestion());
            option1.setText(currQues.getOption1());
            option2.setText(currQues.getOption2());
            option3.setText(currQues.getOption3());

            questionNum++;
            quesNumber.setText("Question : " + questionNum);
            answered = false;
            confirm.setText("Confirm Answer");
        }
        else{
            confirm.setText("Finish");
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishQuiz();
                }
            });

        }
    }

    private void checkAnswer(){
        answered = true;

        int answer = currQues.getAnswer();
        RadioButton givenAnsButton = findViewById(radioGroup.getCheckedRadioButtonId());
        int givenAns = radioGroup.indexOfChild(givenAnsButton) +1;

        if(givenAns == answer){
            score++;
            marks.setText(("Marks : "+ score +"/"+questionsCount));
        }

        showSolution();

    }

    private void showSolution(){
        int answer = currQues.getAnswer();

        option1.setTextColor(Color.RED);
        option2.setTextColor(Color.RED);
        option3.setTextColor(Color.RED);

        switch (answer){
            case 1 :
                option1.setTextColor(Color.GREEN);
                break;
            case 2 :
                option2.setTextColor(Color.GREEN);
                break;
            case 3 :
                option3.setTextColor(Color.GREEN);
                break;
        }
        confirm.setText("Next");
    }
    private void finishQuiz(){
        finish();
    }
}

