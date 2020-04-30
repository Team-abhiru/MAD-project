package com.example.scienceguider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class Feedback_view extends AppCompatActivity {

    ListView listView_feedback;

    List<Feedback> feedback_list;
    DatabaseReference reference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_view);

        listView_feedback = findViewById(R.id.feedback_view);
        feedback_list = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Feedbacks");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Feedback feedbackObj = null;

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    feedbackObj = snapshot.getValue(Feedback.class);

                    feedback_list.add(feedbackObj);
                }

                Feedback_list list = new Feedback_list(Feedback_view.this,feedback_list);

                listView_feedback.setAdapter(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView_feedback.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Feedback feedback = feedback_list.get(position);

                Intent intent = new Intent(Feedback_view.this,Feedback_manage.class);
                intent.putExtra("FEEDBACK_ID", feedback);

                startActivity(intent);
            }
        });
    }

}
