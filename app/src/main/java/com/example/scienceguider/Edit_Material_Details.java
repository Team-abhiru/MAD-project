package com.example.scienceguider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.time.Instant;


public class Edit_Material_Details extends AppCompatActivity {

    private Spinner subjectSp;
    private TextView tpName,edit_notification;
    private String oldSubject, key, oldUrl, topic, subject, url;
    private Button update, cancel,browse;
    private Uri pdfUri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__material__details);


        subjectSp = (Spinner) findViewById(R.id.spinner_subject);
        tpName = findViewById(R.id.tp_name);

        subjectSp = (Spinner) findViewById(R.id.spinner_subject);
        tpName = findViewById(R.id.tp_name);
        update = findViewById(R.id.btn_update);
        cancel= findViewById(R.id.btn_cancel);
        browse = findViewById(R.id.id_btn_edit_browse);
        edit_notification = findViewById(R.id.edit_notification);



        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Edit_Material_Details.this,
                android.R.layout.simple_list_item_1,getResources()
                .getStringArray(R.array.subjects));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        subjectSp.setAdapter(myAdapter);

        subjectSp.setAdapter(myAdapter);



        Intent intent = getIntent();
        final String name = intent.getStringExtra("topName");
        tpName.setText(name);

        oldSubject = intent.getStringExtra("subject");

        oldUrl = intent.getStringExtra("url");
        oldSubject = intent.getStringExtra("subject");
        key = intent.getStringExtra("key");

        System.out.println(key);


        edit_notification.setText(name.trim()+".pfd"+" File is Selected.");

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Materials").child(key);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Edit_Material_Details.this, ShowMaterials.class);
                intent.putExtra("Mat_name", oldSubject);
                intent.putExtra("topic",name);
                intent.putExtra("url",oldUrl);
                intent.putExtra("key",key);
                startActivity(intent);

            }
        });


        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectPDFFile();

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pdfUri != null){

                    uploadPDFFile(pdfUri);


                }else {

                    topic = tpName.getText().toString();
                    subject = subjectSp.getSelectedItem().toString();
                    url = oldUrl;

                    updateMaterial(topic, subject, url, key);

                }

            }
        });
    }

    private void selectPDFFile() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select New PDF File"), 1 );

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){

            pdfUri = data.getData();
            edit_notification.setText("A New file is selected : "+ data.getData().getLastPathSegment());


        }
    }


    private void uploadPDFFile(Uri data) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Updating...");
        progressDialog.setProgress(0);
        progressDialog.show();

        StorageReference reference = storageReference.child("Materials/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                while (!uri.isComplete()) ;
                Uri newUrl = uri.getResult();

                topic = tpName.getText().toString();
                subject = subjectSp.getSelectedItem().toString();

                updateMaterial(topic, subject, newUrl.toString(), key);

            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                progressDialog.dismiss();

                if (!task.isSuccessful()){
                    Toast.makeText(Edit_Material_Details.this, "File Successfully Uploaded", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Edit_Material_Details.this, "File not Successfully Uploaded", Toast.LENGTH_SHORT).show();
                Toast.makeText(Edit_Material_Details.this, "Failed Material Update...", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

                int progress = (int) (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                progressDialog.setMessage("Update " + (int) progress + "%");

            }
        });

    }

    private boolean updateMaterial(String topic, final String subject, String url, String key){
        
       DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Materials").child(key);

        PDF_Uploader uploader = new PDF_Uploader(topic,subject, url,key);

        databaseReference.setValue(uploader).addOnCompleteListener(new OnCompleteListener<Void>() {

        @Override
        public void onComplete(@NonNull Task<Void> task) {

            if (task.isSuccessful()) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Edit_Material_Details.this, "Material Updated...", Toast.LENGTH_LONG).show();

                    }

                }, 500);

                Intent intent = new Intent(Edit_Material_Details.this, Show_Material_List.class);
                intent.putExtra("Mat_name", subject);
                startActivity(intent);
            } else {

                Toast.makeText(Edit_Material_Details.this, "Material Update Failed...", Toast.LENGTH_SHORT).show();
            }
        }
    });

        return true;
}


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            selectPDFFile();

        }
        else {

            Toast.makeText(Edit_Material_Details.this, "Please Provide Permission...", Toast.LENGTH_SHORT).show();

        }
    }

}
