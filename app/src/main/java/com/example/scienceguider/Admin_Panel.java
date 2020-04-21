package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Panel extends AppCompatActivity {

    Button addNewQuestion;
    Button showQuestion;
    Button showUsers;
    Button showFeedback;
    Button addMaterials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__panel);

        addNewQuestion = findViewById(R.id.btn_add_question);
        showQuestion = findViewById(R.id.btn_show_question);
        showUsers = findViewById(R.id.btn_show_users);
        showFeedback = findViewById(R.id.btn_show_feedback);
        addMaterials = findViewById(R.id.btn_add_material);

        showQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(Admin_Panel.this,Quiz_list_admin_view.class);

                 startActivity(intent);
            }
        });

        addNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Admin_Panel.this,Add_new_Quiz.class);

                startActivity(intent);
            }
        });

    }
}
