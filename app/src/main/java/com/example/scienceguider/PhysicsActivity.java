package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhysicsActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physics);

        listView = (ListView) findViewById(R.id.phyListView);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Introduction");
        arrayList.add("Force and Pressure");
        arrayList.add("Friction");
        arrayList.add("Some Natural Phenomena");
        arrayList.add("Motion");
        arrayList.add("Force and Laws of Motion");
        arrayList.add("Gravitation");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(PhysicsActivity.this,"Clicked "
                        +arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
