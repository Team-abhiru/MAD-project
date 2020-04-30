package com.example.scienceguider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Temp extends AppCompatActivity {
    Button admin;
    Button user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        admin = (Button) findViewById(R.id.btn_attemt_quiz);
        user = (Button) findViewById(R.id.btn_admin_quiz);


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Log.i("app","Admin view");
                showadminPlanel();
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowUserView();
            }
        });


    }


    private void showadminPlanel() {
        startActivity(new Intent(Temp.this,Admin_Panel.class));
    }

    private void ShowUserView() {
        startActivity(new Intent(Temp.this, User_Subject_Main.class));
    }
}
