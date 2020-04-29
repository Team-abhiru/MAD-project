package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User_Subject_Main extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    String material;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__subject__main);

        button1 = findViewById(R.id.btnBio);
        button2 = findViewById(R.id.btnPhy);
        button3 = findViewById(R.id.btnChe);


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

    }

    public void openBiologyActivity() {
        material = "Biology";
        Intent intent = new Intent(this, Show_Material_List.class);
        intent.putExtra("Mat_name", material);
        startActivity(intent);
    }

    public void openPhysicsActivity() {
        material = "Physics";
        Intent intent = new Intent(this, Show_Material_List.class);
        intent.putExtra("Mat_name", material);
        startActivity(intent);
    }

    public void openBChemistryActivity() {
        material = "Chemistry";
        Intent intent = new Intent(this, Show_Material_List.class);
        intent.putExtra("Mat_name", material);
        startActivity(intent);
    }
}
