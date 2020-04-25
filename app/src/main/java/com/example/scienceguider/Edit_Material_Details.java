package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Edit_Material_Details extends AppCompatActivity {

    private Spinner subject;
    TextView tpName;
    String sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__material__details);

        subject = (Spinner) findViewById(R.id.spinner_subject);
        tpName = findViewById(R.id.tp_name);

        ArrayAdapter<String> myAdapter = new ArrayAdapter(Edit_Material_Details.this,
                android.R.layout.simple_list_item_1,getResources()
                .getStringArray(R.array.subjects));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subject.setAdapter(myAdapter);

        Intent intent = getIntent();
        String name = intent.getStringExtra("topName");
        tpName.setText(name);
        sub = intent.getStringExtra("subject");
    }
}
