package com.example.scienceguider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText  Memail, Mpassword,Mconfirm_password;
    Button register;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        Memail = findViewById(R.id.email);
        Mpassword = findViewById(R.id.password);
        Mconfirm_password = findViewById(R.id.confirm_password);
        register = findViewById(R.id.register);
        progressBar = findViewById(R.id.RprogressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Memail.getText().toString().trim();
                String password = Mpassword.getText().toString().trim();
                String confirm_password = Mconfirm_password.getText().toString().trim();



                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this,"Please enter email",Toast.LENGTH_SHORT);
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this,"Please enter password",Toast.LENGTH_SHORT);
                    return;
                }

                if(TextUtils.isEmpty(confirm_password)){
                    Toast.makeText(RegisterActivity.this,"Please confirm the password",Toast.LENGTH_SHORT);
                    return;
                }


                if(password.length()<6){

                    Toast.makeText(RegisterActivity.this,"Password too short",Toast.LENGTH_SHORT);

                }

                progressBar.setVisibility(View.VISIBLE);

                if(password.equals(confirm_password)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(),Login.class));
                                        Toast.makeText(RegisterActivity.this,"Registration Successful", Toast.LENGTH_SHORT);

                                    } else {

                                        Toast.makeText(RegisterActivity.this,"Authentication Failed",Toast.LENGTH_SHORT);
                                    }

                                    // ...
                                }
                            });
                }

            }
        });

    }
}
