package com.example.scienceguider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Quiz_list_admin_view extends AppCompatActivity {

    private Question questionArray = null;
    private ListView listView;

    private List<Question> questionList;

    private DatabaseReference refDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list_admin_view);

        refDB = FirebaseDatabase.getInstance().getReference("Questions");

        listView = findViewById(R.id.id_quiz_list_admin);

        questionList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        refDB.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("GetValues", "Get values to an array");
                questionList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    questionArray = snapshot.getValue(Question.class);
                    questionList.add(questionArray);
                }
                QuestionList adapter = new QuestionList(Quiz_list_admin_view.this,questionList);

                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error");
            }
        });
    }
}
