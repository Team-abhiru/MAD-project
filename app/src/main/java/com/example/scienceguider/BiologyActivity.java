package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BiologyActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biology);

        listView = (ListView) findViewById(R.id.phyListView);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Structure and Function");
        arrayList.add("The Fundamental Unit of Life");
        arrayList.add("Tissues");
        arrayList.add("Animal Tissue");
        arrayList.add("Diversity in Living Organisms");
        arrayList.add("Plantae Kingdom");
        arrayList.add("Kingdom Animalia");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(BiologyActivity.this,"Clicked "
                        +arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
