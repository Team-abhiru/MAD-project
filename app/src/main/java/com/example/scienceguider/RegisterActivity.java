package com.example.scienceguider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText Musername,Mname,Memail,Mschool,Mpassword;
    Button register;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Musername = findViewById(R.id.username);
        Mname = findViewById(R.id.name);
        Memail = findViewById(R.id.email);
        Mschool = findViewById(R.id.school);
        Mpassword = findViewById(R.id.password);
        register = findViewById(R.id.register);

        fAuth = FirebaseAuth.getInstance();

//        if (fAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(),Login.class));
//            finish();
//        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Memail.getText().toString().trim();
                String password = Mpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Memail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Mpassword.setError("Password is Required.");
                    return;
                }

                if(password.length()  <  6){
                    Mpassword.setError("Password Must be >=6 characters");
                    return;
                }


                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));

                        }else{
                            Toast.makeText(RegisterActivity.this, "Error !!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
