package com.example.scienceguider;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ShowMaterials extends AppCompatActivity {

    ImageView edit, delete;
    TextView topic;
    String url, name, sub;
    Button pdfButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_materials);

        edit = findViewById(R.id.image_edit);
        delete = findViewById(R.id.image_delete);
        topic = findViewById(R.id.showTpUser);
        pdfButton = findViewById(R.id.userPdfDownload);

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

        pdfButton.setText(name+".pdf");

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
    public void openEditMaterialActivity() {

        Intent intent = new Intent(this, Edit_Material_Details.class);
        intent.putExtra("topName", name);
        intent.putExtra("subject", sub);
        startActivity(intent);
    }

}
