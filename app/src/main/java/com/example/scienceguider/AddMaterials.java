package com.example.scienceguider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Objects;

public class AddMaterials extends AppCompatActivity {

    private EditText Tp_name;
    private Button id_btn_browse;
    private Uri pdfUri;
    private Button upload;
    private TextView add_notification;
    private Spinner subject;
//    Spinner spinner_subject;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmaterials);

        subject = (Spinner) findViewById(R.id.spinner_subject);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddMaterials.this,
                android.R.layout.simple_list_item_1,getResources()
                .getStringArray(R.array.subjects));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subject.setAdapter(myAdapter);

        Tp_name = findViewById(R.id.Tp_name);
        id_btn_browse = findViewById(R.id.id_btn_browse);
        upload = findViewById(R.id.upload);

        add_notification = findViewById(R.id.add_notification);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Materials");


        id_btn_browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectPDFFile();
            }

        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pdfUri != null){
                    uploadPDFFile(pdfUri);

                }
                else
                    Toast.makeText(AddMaterials.this, "Select a File", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void selectPDFFile() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF File"), 1 );

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){

           pdfUri = data.getData();
           add_notification.setText("A file is selected : "+ data.getData().getLastPathSegment());


        }
    }

    private void uploadPDFFile(Uri data) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.setProgress(0);
        progressDialog.show();

        StorageReference reference = storageReference.child("Materials/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                while (!uri.isComplete()) ;

                Uri url = uri.getResult();

                String selected_subject = subject.getSelectedItem().toString();

                PDF_Uploader uploader = new PDF_Uploader(Tp_name.getText().toString(),selected_subject, url.toString());
                databaseReference.child(databaseReference.push().getKey()).setValue(uploader).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        progressDialog.dismiss();

                        if(task.isSuccessful()) {

                            Toast.makeText(AddMaterials.this, "File Successfully uploaded", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(AddMaterials.this,"File not Successfully uploaded", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

                int progress = (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());

                progressDialog.setMessage("Uploaded "+(int)progress+"%");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddMaterials.this, "File not Successfully Uploaded", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            selectPDFFile();
        } else
            Toast.makeText(AddMaterials.this, "Please Provide Permission...", Toast.LENGTH_SHORT).show();


    }
}
