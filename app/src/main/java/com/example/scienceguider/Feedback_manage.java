package com.example.scienceguider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback_manage extends AppCompatActivity {

    TextView textView_subject;
    TextView textView_feedback;
    TextView textView_comment;

    Button button_delete;
    Button button_update;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_manage);

        textView_subject = (TextView) findViewById(R.id.textView_Subject);
        textView_feedback = (TextView) findViewById(R.id.textView_feedback);
        textView_comment = (TextView) findViewById(R.id.textView_comment);

        button_delete = (Button) findViewById(R.id.feedback_delete);
        button_update = (Button) findViewById(R.id.feedback_edit);

        Intent getIntent = getIntent();

        final Feedback feedbackObj = (Feedback) getIntent.getSerializableExtra("FEEDBACK_ID");

        reference = FirebaseDatabase.getInstance().getReference("Feedbacks").child(feedbackObj.getFeedbackID());

        textView_subject.setText(feedbackObj.getSubject());
        textView_feedback.setText(feedbackObj.getFeedback());
        textView_comment.setText(feedbackObj.getComment());

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue();
                Toast.makeText(Feedback_manage.this,"Feedback is deleted",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(Feedback_manage.this,Feedback_view.class));
            }
        });

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update = new Intent(Feedback_manage.this,Update_feedback.class);
                update.putExtra("FEEDBACK_OBJECT",feedbackObj);

                startActivity(update);
            }
        });

    }
}
