package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Show_Material_List extends AppCompatActivity {

    ListView listView;
    TextView Mat_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__material__list);

        Mat_name = findViewById(R.id.mat_name);
        listView = (ListView) findViewById(R.id.phyListView);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Mat_name");


        Mat_name.setText(name);

//        final ArrayList<String> arrayList = new ArrayList<>();


//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

//        listView.setAdapter(arrayAdapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(BiologyActivity.this,"Clicked "
//                        +arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
