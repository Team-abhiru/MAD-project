package com.example.scienceguider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    private Question questionRef = null;
    private ListView listView;

    private Spinner subject;
    private Spinner topic;

    private List<Question> questionList;
    private QuestionList adapter;

    private DatabaseReference refDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list_admin_view);

        refDB = FirebaseDatabase.getInstance().getReference("Questions");

        listView = findViewById(R.id.id_quiz_list_admin);
        subject = (Spinner) findViewById(R.id.id_subject_list) ;
        topic = (Spinner) findViewById(R.id.id_topic_list) ;

        questionList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                questionRef = questionList.get(position);

                showQuestion(questionRef);
            }
        });

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//                questionRef = questionList.get(position);
//
//                showUpdate(questionRef);
//                return false;
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        refDB.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Log.i("GetValues", "Get values to an array");

                        questionList.clear();

                        String topicName = topic.getSelectedItem().toString();
                        String subjectName = subject.getSelectedItem().toString();

                        System.out.println(subjectName);
                        System.out.println(topicName);

                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            questionArray = snapshot.getValue(Question.class);

                            if(questionArray.getSubject().contentEquals(subjectName) && questionArray.getTopic().contentEquals(topicName)) {
                                questionList.add(questionArray);
                                System.out.println(topicName);
                            }
                        }
                        adapter = new QuestionList(Quiz_list_admin_view.this, questionList);

                        listView.setAdapter(adapter);
                        System.out.println(topicName);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                topic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Log.i("GetValues", "Get values to an array");

                        questionList.clear();

                        String topicName = topic.getSelectedItem().toString();
                        String subjectName = subject.getSelectedItem().toString();

                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            questionArray = snapshot.getValue(Question.class);

                            if(questionArray.getSubject().contentEquals(subjectName) && questionArray.getTopic().contentEquals(topicName)) {
                                questionList.add(questionArray);
                            }
                        }
                        adapter = new QuestionList(Quiz_list_admin_view.this, questionList);

                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error");
            }
        });

    }

    private void showUpdate(final Question questionObj){
        AlertDialog.Builder diaBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_question,null);

        diaBuilder.setView(dialogView);
        final EditText edit_question = (EditText) dialogView.findViewById(R.id.id_edit_write_question);
        final EditText edit_option1 = (EditText) dialogView.findViewById(R.id.text_view_edit_option1);
        final EditText edit_option2 = (EditText) dialogView.findViewById(R.id.text_view_edit_option2);
        final EditText edit_option3 = (EditText) dialogView.findViewById(R.id.text_view_edit_option3);
        final EditText edit_Answer = (EditText) dialogView.findViewById(R.id.id_edit_add_answer);

        final Spinner edit_topic = (Spinner) dialogView.findViewById(R.id.edit_spinner);
        final Spinner edit_subject = (Spinner) dialogView.findViewById(R.id.edit_spinner2);

        final Button btn_update = (Button) dialogView.findViewById(R.id.id_btn_update);

        edit_question.setText(questionObj.getQuestion());
        edit_option1.setText(questionObj.getOption1());
        edit_option2.setText(questionObj.getOption2());
        edit_option3.setText(questionObj.getOption3());
//        edit_Answer.setText();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String up_question =  edit_question.getText().toString();
               String up_option1 =edit_option1.getText().toString();
               String up_option2 = edit_option2.getText().toString();
               String up_option3 =edit_option3.getText().toString();
               String up_answer =edit_Answer.getText().toString();
               String up_subject =edit_subject.getSelectedItem().toString();
               String up_topic =edit_topic.getSelectedItem().toString();

                if(up_option1.isEmpty() || up_option2.isEmpty() || up_option3.isEmpty()
                        || up_answer.isEmpty() || up_question.isEmpty() || up_subject.contentEquals("Subject")
                        || up_topic.contentEquals("Topic")){

                    Toast.makeText(Quiz_list_admin_view.this,"Fields cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    int answer = Integer.parseInt(edit_Answer.getText().toString());

                    final String QID = questionObj.getQID();

                    DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference("Questions").child(QID);

                    Question quesnObj = new Question(up_question, answer, up_option1, up_option2, up_option3,up_subject, up_topic, QID );


                    updateRef.setValue(quesnObj);

                    Toast.makeText(Quiz_list_admin_view.this,"Question is updated",Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Quiz_list_admin_view.this,Quiz_list_admin_view.class));
                }
            }
        });
        diaBuilder.setTitle("Update Question");

        AlertDialog alertDialog = diaBuilder.create();
        alertDialog.show();
    }

    public void showQuestion(final Question question){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.view_question,null);

        builder.setView(dialogView);
        final TextView view_question = (TextView) dialogView.findViewById(R.id.textView_show_question);
        final TextView view_option1 = (TextView) dialogView.findViewById(R.id.textView_show_option1);
        final TextView view_option2 = (TextView) dialogView.findViewById(R.id.textView_show_option2);
        final TextView view_option3 = (TextView) dialogView.findViewById(R.id.textView_show_option3);
        final TextView view_Answer = (TextView) dialogView.findViewById(R.id.textView_show_answer);

        final Button btn_update = (Button) dialogView.findViewById(R.id.btn_show_update);
        final Button btn_delete = (Button) dialogView.findViewById(R.id.btn_delete);

        view_question.setText(question.getQuestion());
        view_option1.setText(question.getOption1());
        view_option2.setText(question.getOption2());
        view_option3.setText(question.getOption3());
//        view_Answer.setText(question.getAnswer());

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdate(question);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference("Questions").child(question.getQID());

                deleteRef.removeValue();
                Toast.makeText(Quiz_list_admin_view.this,"Question is deleted",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Quiz_list_admin_view.this,Quiz_list_admin_view.class));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
