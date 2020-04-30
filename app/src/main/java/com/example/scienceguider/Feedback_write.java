package com.example.scienceguider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback_write extends AppCompatActivity {

EditText editText_comment;
RadioGroup subject_group;
RadioGroup feedback_group;

RadioButton radioButton_physics;
RadioButton radioButton_Biology;
RadioButton radioButton_Chemistry;

RadioButton radioButton_excellent;
RadioButton radioButton_Verygood;
RadioButton radioButton_good;
RadioButton radioButton_bad;

Button send_feedback;

DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_write);

        editText_comment = (EditText) findViewById(R.id.comment);
        subject_group = (RadioGroup) findViewById(R.id.subject_rad_group);
        feedback_group = (RadioGroup) findViewById(R.id.comment_rad_group);

        radioButton_physics = (RadioButton) findViewById(R.id.feed_physics);
        radioButton_Biology = (RadioButton) findViewById(R.id.feed_biology);
        radioButton_Chemistry = (RadioButton) findViewById(R.id.feed_chemistry);

        radioButton_excellent = (RadioButton) findViewById(R.id.radio_excellent);
        radioButton_Verygood = (RadioButton) findViewById(R.id.radio_verygood);
        radioButton_good = (RadioButton) findViewById(R.id.radio_good);
        radioButton_bad = (RadioButton) findViewById(R.id.radio_bad);

        send_feedback = (Button) findViewById(R.id.btn_send_feedback);

        reference = FirebaseDatabase.getInstance().getReference("Feedbacks");

        send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendFeedback();
            }
        });
    }

    private void sendFeedback() {

        String comment ;
        String subject = null;
        String feedback = null;

        comment = editText_comment.getText().toString();

        if(comment.isEmpty()){
            Toast.makeText(Feedback_write.this,"Please write comment",Toast.LENGTH_SHORT).show();

        }

        if(radioButton_physics.isChecked() || radioButton_Biology.isChecked() || radioButton_Chemistry.isChecked()) {

            RadioButton select_subject = findViewById(subject_group.getCheckedRadioButtonId());
            int subject_selected_button = subject_group.indexOfChild(select_subject) + 1;

            switch (subject_selected_button) {

                case 1:
                    subject = "physics";
                    break;
                case 2:
                    subject = "chemistry";
                    break;
                case 3:
                    subject = "biology";
                    break;

            }
        }else{

            Toast.makeText(Feedback_write.this,"Please select subject",Toast.LENGTH_SHORT).show();
        }

        if(radioButton_excellent.isChecked() || radioButton_Verygood.isChecked() ||
                radioButton_good.isChecked() || radioButton_bad.isChecked()) {

            RadioButton select_feedback = findViewById(feedback_group.getCheckedRadioButtonId());
            int feedback_selected_button = feedback_group.indexOfChild(select_feedback) + 1;

            switch (feedback_selected_button) {

                case 1:
                    feedback = "excellent";
                    break;
                case 2:
                    feedback = "verygood";
                    break;
                case 3:
                    feedback = "good";
                    break;
                case 4:
                    feedback = "bad";
                    break;

            }
        }else{

            Toast.makeText(Feedback_write.this,"Please select feedback",Toast.LENGTH_SHORT).show();
        }

        if(!comment.isEmpty() || !feedback.isEmpty() || !subject.isEmpty()){
            String ID = reference.push().getKey();

            Feedback feedbackObj = new Feedback(comment,subject,feedback,ID);

            reference.child(ID).setValue(feedbackObj);
            Toast.makeText(Feedback_write.this,"Thank you for your feedback",Toast.LENGTH_LONG).show();

            feedback_group.clearCheck();
            subject_group.clearCheck();
            editText_comment.setText("");

        }


    }


}
