package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class Quiz_list_admin_view extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list_admin_view);

        String [] questions = {"Question 1", "Question 2", "Question 3", "Question 4"};

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_quiz_admin_view, R.id.id_quize_admin, questions);

        listView = findViewById(R.id.id_quiz_list_admin);
        listView.setAdapter(arrayAdapter);

    }
}
