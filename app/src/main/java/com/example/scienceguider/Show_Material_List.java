package com.example.scienceguider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.Transliterator;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.errorprone.annotations.SuppressPackageLocation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class Show_Material_List extends AppCompatActivity {

    ListView listView;
    List<PDF_Uploader> materialList;
    TextView Mat_name;

    Intent intent = getIntent();
    String name = intent.getStringExtra("Mat_name");

    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__material__list);

        listView = (ListView) findViewById(R.id.ListView);
        Mat_name = findViewById(R.id.mat_name);
        materialList = new ArrayList<>();

        Mat_name.setText(name);

        viewAllFiles();

    }

    private void viewAllFiles() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Materials");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    PDF_Uploader uploader = postSnapshot.getValue(PDF_Uploader.class);
                    materialList.add(uploader);

                }

                String[] material = new String[materialList.size()];
                String[] subject = new String[materialList.size()];

                for (int i = 0; i < material.length; i++ ){

                    subject[i] = materialList.get(i).getSubject();

                    if( subject[i] == name) {

                        material[i] = materialList.get(i).getTopic();
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,material);

                listView.setAdapter(adapter);

                viewAllFiles();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        PDF_Uploader uploader = materialList.get(position);

//                        Intent intent = new Intent();
//                        intent.setType(Intent.ACTION_VIEW);
//                        intent.setData(Uri.parse(uploader.getUrl()));
//                        startActivity(intent);

                        Intent intent = new Intent(Show_Material_List.this, ShowMaterials.class);
                        intent.putExtra("url",Uri.parse(uploader.getUrl()).toString());
                        intent.putExtra("topic",uploader.topic);
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
