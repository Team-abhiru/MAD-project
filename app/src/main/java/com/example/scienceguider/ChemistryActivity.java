package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChemistryActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemistry);

        listView = (ListView) findViewById(R.id.phyListView);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Matter in our Surroundings");
        arrayList.add("Is Matter Around us Pure");
        arrayList.add("Atoms & Molecules");
        arrayList.add("Structure of the Atom");
        arrayList.add("Chemical Reactions and Equations");
        arrayList.add("Acids, Bases, and Salts");
        arrayList.add("Materials: Metals and Non-metals 1");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ChemistryActivity.this,"Clicked "
                        +arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
