package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Quiz_list_user_view extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list_user_view);

        //create an array to use as data set
        String questions [] = {"Question 1", "Question 2", "Question 3", "Question 4", "Question 5"};

        //Use array adapter to create a list
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_quiz_user_view, R.id.id_quiz_user,questions);

        listView = findViewById(R.id.id_quiz_list_user);
        listView.setAdapter(arrayAdapter);
    }
}
