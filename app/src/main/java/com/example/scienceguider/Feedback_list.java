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

public class Feedback_list extends ArrayAdapter {

    private Activity context;
    private List<Feedback> list;

    public Feedback_list(@NonNull Activity context, List<Feedback> list) {
        super(context, R.layout.feedback_view_layout,list);

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listView = inflater.inflate(R.layout.feedback_view_layout,null,true);

        TextView textView_feedback = (TextView) listView.findViewById(R.id.id_feedback_row);
        TextView textView_subject = (TextView) listView.findViewById(R.id.feedback_Subject);

        Feedback feedback = list.get(position);

        textView_feedback.setText(feedback.getComment());
        textView_subject.setText(feedback.getSubject());

        return listView;
    }
}
