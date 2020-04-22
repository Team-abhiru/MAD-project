package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TempActivity extends AppCompatActivity {

    Button attempt_quiz;
    Button admin_quiz_view;
    Button add_quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

//        attempt_quiz = (Button) findViewById(R.id.btn_attemt_quiz);
//        admin_quiz_view = (Button) findViewById(R.id.btn_admin_quiz);
//        add_quiz = (Button) findViewById(R.id.id_add_quiz);
//
//        admin_quiz_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showQuizzes();
//            }
//        });
//
//        attempt_quiz.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                attemptQuiz();
//            }
//        });
//
//        add_quiz.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addQuiz();
//            }
//        });
    }

//    private void addQuiz() {
//        startActivity(new Intent(TempActivity.this,Add_new_Quiz.class));
//    }
//
//    private void showQuizzes() {
//        startActivity(new Intent(TempActivity.this,Quiz_list_user_view.class));
//    }
//
//    private void attemptQuiz() {
//
//        startActivity(new Intent(TempActivity.this,Quiz_list_user_view.class));
//    }
}
