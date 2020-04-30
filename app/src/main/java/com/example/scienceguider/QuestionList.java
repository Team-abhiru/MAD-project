package com.example.scienceguider;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class QuestionList extends ArrayAdapter<Question> {
    private Activity context;
    private List<Question> list;

    public QuestionList(Activity context, List<Question> questionList){
        super(context,R.layout.activity_quiz_admin_view,questionList);
        this.context = context;
        this.list = questionList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listView = inflater.inflate(R.layout.activity_quiz_admin_view,null,true);

        TextView textView_question = (TextView) listView.findViewById(R.id.id_quize_admin);

        Question question = list.get(position);

        textView_question.setText(question.getQuestion());
        return  listView;
    }
}
