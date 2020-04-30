package com.example.scienceguider;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class User_Show_Material extends AppCompatActivity {

    ImageView edit, delete;
    TextView topic;
    String url, name, subject;
    Button pdfButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__show__material);

        edit = findViewById(R.id.image_edit);
        delete = findViewById(R.id.image_delete);
        topic = findViewById(R.id.showTpUser);
        pdfButton = findViewById(R.id.userPdfDownload);

        Intent intent = getIntent();
        name = intent.getStringExtra("topic");
        topic.setText(name);
        url = intent.getStringExtra("url");
        subject = intent.getStringExtra("Mat_name");

        pdfButton.setText(name.replace(" ","")+".pdf");

        pdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        Intent i = new Intent(User_Show_Material.this, User_Show_Material_List.class);
        i.putExtra("Mat_name",subject);

    }




}
