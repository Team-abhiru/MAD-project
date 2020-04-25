package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    String material;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.btnBio);
        button2 = findViewById(R.id.btnPhy);
        button3 = findViewById(R.id.btnChe);


        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openBiologyActivity();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openPhysicsActivity();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
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

    public void AddMaterials(View view) {
        Intent intent = new Intent(this, AddMaterials.class);
        startActivity(intent);

    }
    
}