package com.example.scienceguider;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewActivity extends AppCompatActivity {

    ListView listView_file;

    List<Upload_file> upload_fileList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_recycler_view);

        listView_file = (ListView) findViewById(R.id.file_list_view);

        upload_fileList = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Upload");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Upload_file upload_file = snapshot.getValue(Upload_file.class);
                    upload_fileList.add(upload_file);
                }

                MyAdapter adapter = new MyAdapter(MyRecyclerViewActivity.this,upload_fileList);
                listView_file.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//
//                    String fileName = dataSnapshot.getKey();
//                    String url = dataSnapshot.getValue();
//
//                    ((MyAdapter) recyclerView.getAdapter()).update(fileName, url);
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        recyclerView = findViewById(R.id.recyckerView);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(MyRecyclerViewActivity.this));
//        MyAdapter myAdapter = new MyAdapter(recyclerView,MyRecyclerViewActivity.this,new ArrayList<String>(),new ArrayList<String>());
//        recyclerView.setAdapter(myAdapter);
    }
}
