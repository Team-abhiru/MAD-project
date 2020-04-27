package com.example.scienceguider;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import java.util.Locale;

public class Attempt_quiz extends AppCompatActivity {
    private static final long COUNT_DOWN_TIME = 30000;

    private TextView textView_question;
    private TextView textView_review;
    private TextView textView_marks;
    private TextView textView_subject;
    private TextView textView_quesNumber;
    private TextView textView_counter;
    private RadioGroup radioGroup;
    private RadioButton option1, option2, option3;
    private Button confirm;

    private int questionsCount = 0;
    private int questionNum = 0;
    private int score;
    private long countdown;

    private ColorStateList defaultColor;
    private ColorStateList defaultColorCD;

    private CountDownTimer countDownTimer;
    private Question currQues;
    private Question questionArray = null;
    private Boolean answered;
    private List<Question> questionList;

    private DatabaseReference refDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attempt_quiz);

        Log.i("State","Create");

        refDB = FirebaseDatabase.getInstance().getReference("Questions");

        questionList = new ArrayList<>();

        textView_question = (TextView) findViewById(R.id.id_question);
        textView_marks = (TextView) findViewById(R.id.id_score);
        textView_subject = (TextView) findViewById(R.id.id_subject);
        textView_quesNumber = (TextView) findViewById(R.id.id_question_number);
        textView_counter = (TextView) findViewById(R.id.id_counter);
        textView_review = (TextView) findViewById(R.id.id_review);

        radioGroup = (RadioGroup) findViewById(R.id.id_radio_group);
        option1 = (RadioButton) findViewById(R.id.id_answer1);
        option2 = (RadioButton) findViewById(R.id.id_answer2);
        option3 = (RadioButton) findViewById(R.id.id_answer3);

        confirm = (Button) findViewById(R.id.id_btn_confirm);

        defaultColor = option1.getTextColors();
        defaultColorCD = textView_counter.getHintTextColors();

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
                                Toast.makeText(Attempt_quiz.this,"Select a answer",Toast.LENGTH_SHORT).show();
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
    }

    private void getQuestion(){
        Log.i("State","Get question");

        if(questionNum < questionsCount){
            option1.setTextColor(defaultColor);
            option2.setTextColor(defaultColor);
            option3.setTextColor(defaultColor);

            radioGroup.clearCheck();

            currQues = questionList.get(questionNum);

            textView_question.setText(currQues.getQuestion());
            option1.setText(currQues.getOption1());
            option2.setText(currQues.getOption2());
            option3.setText(currQues.getOption3());

            questionNum++;

            textView_quesNumber.setText("Question : " + questionNum);
            answered = false;
            confirm.setText("Confirm Answer");
            countdown = COUNT_DOWN_TIME;

            textView_review.setText("");
            startCountDown();
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

        countDownTimer.cancel();

        int answer = currQues.getAnswer();
        RadioButton givenAnsButton = findViewById(radioGroup.getCheckedRadioButtonId());
        int givenAns = radioGroup.indexOfChild(givenAnsButton) +1;

        if(givenAns == answer){
            score++;
            textView_marks.setText(("Marks : "+ score +"/"+questionsCount));
            textView_review.setText("Well done Correct Answer");
        }
        else {
            textView_review.setText("Wrong answer do more exercises");
            textView_marks.setText(("Marks : "+ score +"/"+questionsCount));
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
                option1.setTextColor(Color.BLUE);
                break;
            case 2 :
                option2.setTextColor(Color.BLUE);
                break;
            case 3 :
                option3.setTextColor(Color.BLUE);
                break;
        }

        if(questionNum < questionsCount) {
            confirm.setText("Next");
        }else{
            confirm.setText("Finish");
            getQuestion();
        }
    }

    private void finishQuiz(){
        finish();
    }

    private void startCountDown(){

        countDownTimer = new CountDownTimer(countdown,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countdown = millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {
                countdown = 0;
                updateCountDown();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDown(){
        int minute = (int) ((countdown / 1000)/60);
        int second = (int) ((countdown/1000)%60);

        String format = String.format(Locale.getDefault(),"%02d:%02d",minute,second);
        textView_counter.setText(format);
        System.out.println(countdown);
        if(countdown < 10000){
            textView_counter.setTextColor(Color.RED);
        }else{
            textView_counter.setTextColor(defaultColorCD);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}

