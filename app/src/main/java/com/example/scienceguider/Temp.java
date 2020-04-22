package com.example.scienceguider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Temp extends AppCompatActivity {
    Button attempt_quiz;
    Button admin_quiz_view;
    Button add_quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        attempt_quiz = (Button) findViewById(R.id.btn_attemt_quiz);
        admin_quiz_view = (Button) findViewById(R.id.btn_admin_quiz);
        add_quiz = (Button) findViewById(R.id.id_add_quiz);

        admin_quiz_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Log.i("app","Admin view");
                showQuizzes();
            }
        });

        attempt_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptQuiz();
            }
        });

        add_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("app","Add quiz");
                addQuiz();
            }
        });
    }

    private void addQuiz() {
        startActivity(new Intent(Temp.this,Add_new_Quiz.class));
    }

    private void showQuizzes() {
        startActivity(new Intent(Temp.this,Quiz_list_user_view.class));
    }

    private void attemptQuiz() {

        startActivity(new Intent(Temp.this,Quiz_list_user_view.class));
    }
}
