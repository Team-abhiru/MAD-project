package com.example.scienceguider;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class ShowMaterials extends AppCompatActivity {

    ImageView edit, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_materials);

        edit = findViewById(R.id.image_edit);
        delete = findViewById(R.id.image_delete);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditMaterialActivity();
            }

        });

    }
    public void openEditMaterialActivity() {

        Intent intent = new Intent(this, Edit_Material_Details.class);
        startActivity(intent);
    }

}
