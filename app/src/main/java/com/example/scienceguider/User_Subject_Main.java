package com.example.scienceguider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class User_Subject_Main extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button quiz;
    Button feedback;
    String material;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__subject__main);

        button1 = findViewById(R.id.btnBioUser);
        button2 = findViewById(R.id.btnPhyUser);
        button3 = findViewById(R.id.btnCheUser);
        quiz = findViewById(R.id.id_attemt_quiz);
        feedback = findViewById(R.id.id_add_feedback);


        button1.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                openBiologyActivity();
            }
        });
        button2.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                openPhysicsActivity();
            }
        });
        button3.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                openBChemistryActivity();
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Subject_Main.this, Attempt_quiz.class);
                startActivity(intent);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Subject_Main.this, Feedback_write.class);
                startActivity(intent);
            }
        });

    }

    public void openBiologyActivity() {
        material = "Biology";
        Intent intent = new Intent(this, User_Show_Material_List.class);
        intent.putExtra("Mat_name", material);
        startActivity(intent);
    }

    public void openPhysicsActivity() {
        material = "Physics";
        Intent intent = new Intent(this, User_Show_Material_List.class);
        intent.putExtra("Mat_name", material);
        startActivity(intent);
    }

    public void openBChemistryActivity() {
        material = "Chemistry";
        Intent intent = new Intent(this, User_Show_Material_List.class);
        intent.putExtra("Mat_name", material);
        startActivity(intent);
    }
}
