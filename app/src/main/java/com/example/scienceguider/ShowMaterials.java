package com.example.scienceguider;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ShowMaterials extends AppCompatActivity {

    ImageView edit, delete;
    TextView topic;
    String url, name, sub,key;

    Button pdfButton;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_materials);

        edit = findViewById(R.id.image_edit);
        delete = findViewById(R.id.image_delete);
        topic = findViewById(R.id.showTp);
        pdfButton = findViewById(R.id.pdfDownload);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditMaterialActivity();
            }

        });

        Intent intent = getIntent();
        name = intent.getStringExtra("topic");
        topic.setText(name);
        url = intent.getStringExtra("url");
        sub = intent.getStringExtra("subject");

        key = intent.getStringExtra("key");

        pdfButton.setText(name.replace(" ","")+".pdf");

        Intent i = new Intent(ShowMaterials.this, Show_Material_List.class);
        i.putExtra("Mat_name",sub);

        pdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteMaterial(key);
            }
        });

    }

    private void deleteMaterial(String key) {

        databaseReference = FirebaseDatabase.getInstance().getReference("Materials").child(key);
        databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ShowMaterials.this, "Material Deleted...", Toast.LENGTH_LONG).show();

                        }

                    }, 500);

                    Intent intent = new Intent(ShowMaterials.this, Show_Material_List.class);
                    intent.putExtra("Mat_name",sub);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(ShowMaterials.this, "Material Cannot Delete...", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void openEditMaterialActivity() {

        Intent intent = new Intent(this, Edit_Material_Details.class);
        intent.putExtra("topName", name);
        intent.putExtra("subject", sub);
        intent.putExtra("url",url);
        intent.putExtra("key",key);
        startActivity(intent);
    }




}
