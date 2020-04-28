package com.example.scienceguider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

class User_Show_Material_List extends AppCompatActivity {

    ListView listView;
    List<PDF_Uploader> materialList;
    TextView Mat_name;
    String name;
    String key;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__user__material);

        listView = (ListView) findViewById(R.id.ListView);
        Mat_name = findViewById(R.id.mat_name);
        materialList = new ArrayList<>();

        Intent intent = getIntent();
        name = intent.getStringExtra("Mat_name");

        Mat_name.setText(name+" Materials");

        viewAllFiles();

    }

    private void viewAllFiles() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Materials");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    PDF_Uploader uploader = postSnapshot.getValue(PDF_Uploader.class);
                    materialList.add(uploader);

                }

                List material = new ArrayList();
                final List selectedSub = new ArrayList();


                for (int i = 0; i < materialList.size(); i++ ){


                    if(materialList.get(i).getSubject().compareTo(name) == 0 ) {

                        material.add(materialList.get(i).getTopic());
                        selectedSub.add(materialList.get(i));

                    }
                }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,material);

                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        PDF_Uploader uploader = (PDF_Uploader) selectedSub.get(position);


                        System.out.println(key);

                        Intent intent = new Intent(User_Show_Material_List.this, User_Show_Material.class);
                        intent.putExtra("url", Uri.parse(uploader.getUrl()).toString());
                        intent.putExtra("topic", uploader.topic);
                        startActivity(intent);
                    }

                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
