package com.example.scienceguider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update_feedback extends AppCompatActivity {

    Spinner spinner_feedback;
    Spinner spinner_subject;
    EditText editText_comment;
    Button button_update;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_feedback);

        Intent getIntent = getIntent();

        final Feedback feedbackObj = (Feedback) getIntent.getSerializableExtra("FEEDBACK_OBJECT");

        reference = FirebaseDatabase.getInstance().getReference("Feedbacks").child(feedbackObj.getFeedbackID());

        spinner_feedback = (Spinner) findViewById(R.id.spinner_feedback);
        spinner_subject = (Spinner) findViewById(R.id.spinner_feedback_subject);
        editText_comment = (EditText) findViewById(R.id.edit_comment);
        button_update = (Button) findViewById(R.id.feedback_edit);

        editText_comment.setText(feedbackObj.getComment().toString());

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = spinner_subject.getSelectedItem().toString();
                String feedback = spinner_feedback.getSelectedItem().toString();
                String comment = editText_comment.getText().toString();

                if(subject.contentEquals("Subject") || feedback.contentEquals("Select") || comment.isEmpty()){

                    Toast.makeText(Update_feedback.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }else{

                    Feedback newFeedback = new Feedback(comment,subject,feedback,feedbackObj.getFeedbackID());

                    reference.setValue(newFeedback);
                    Toast.makeText(Update_feedback.this,"Updated",Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Update_feedback.this,Feedback_view.class));
                }

            }
        });

    }
}
