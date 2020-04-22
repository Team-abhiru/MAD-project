package com.example.scienceguider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Add_new_Quiz extends AppCompatActivity {

    private DatabaseReference refDB;

    private EditText add_question;
    private EditText add_answer;
    private EditText answer1;
    private EditText answer2;
    private EditText answer3;
    private Button save;
    private Spinner subject;
    private Spinner topic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new__quiz);

        Log.d("started","App Started");
        refDB = FirebaseDatabase.getInstance().getReference();

        add_question = (EditText) findViewById(R.id.id_write_question);
        add_answer = (EditText) findViewById(R.id.id_add_answer);
        answer1 = (EditText) findViewById(R.id.text_view_option1);
        answer2 = (EditText) findViewById(R.id.text_view_option2);
        answer3 = (EditText) findViewById(R.id.text_view_option3);
        save = (Button) findViewById(R.id.id_btn_save);
        subject = (Spinner) findViewById(R.id.spinner);
        topic = (Spinner) findViewById(R.id.spinner2);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question questionObj = new Question();

                String question = add_question.getText().toString();
                String answer = add_answer.getText().toString();
                String option1 = answer1.getText().toString();
                String option2 = answer2.getText().toString();
                String option3 = answer3.getText().toString();
                String selected_subject = subject.getSelectedItem().toString();
                String selected_topic = topic.getSelectedItem().toString();

                if(option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || answer.isEmpty()
                        || answer.isEmpty() || selected_subject.isEmpty()){

                    Toast.makeText(Add_new_Quiz.this,"Fields cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else{

                    questionObj.setQuestion(question);
                    questionObj.setAnswer(Integer.parseInt(answer));
                    questionObj.setOption1(option1);
                    questionObj.setOption2(option2);
                    questionObj.setOption3(option3);
                    questionObj.setSubject(selected_subject);
                    questionObj.setTopic(selected_topic);

                    refDB.child("Questions").push().setValue(questionObj);

                    Toast.makeText(Add_new_Quiz.this,"New question is added",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
