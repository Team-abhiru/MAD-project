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

    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__material__list);

        listView = (ListView) findViewById(R.id.ListView);
        materialList = new ArrayList<>();

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

                String[] uploads = new String[materialList.size()];

                for (int i = 0; i < uploads.length; i++ ){

                    uploads[i] = materialList.get(i).getTopic();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads);

                listView.setAdapter(adapter);

                viewAllFiles();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        PDF_Uploader uploader = materialList.get(position);

                        Intent intent = new Intent();
                        intent.setType(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(uploader.getUrl()));
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
