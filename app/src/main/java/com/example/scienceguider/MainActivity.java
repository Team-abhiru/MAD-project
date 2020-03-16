package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String questions [] = {"question 1", "question 2", "question 3"};

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_quiz_user_view, R.id.id_quiz,questions);

        listView.findViewById(R.id.id_quiz_list);
        listView.setAdapter(arrayAdapter);
    }
}
