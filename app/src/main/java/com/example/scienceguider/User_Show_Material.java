package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;



class User_Show_Material extends AppCompatActivity {

    ImageView edit, delete;
    TextView topic;
    String url, name, sub, key;
    Button pdfButton;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__user__material);

        edit = findViewById(R.id.image_edit);
        delete = findViewById(R.id.image_delete);
        topic = findViewById(R.id.showTp);
        pdfButton = findViewById(R.id.pdfDownload);

        Intent intent = getIntent();
        name = intent.getStringExtra("topic");
        topic.setText(name);
        url = intent.getStringExtra("url");

        pdfButton.setText(name.trim()+".pdf");

        pdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }

}
