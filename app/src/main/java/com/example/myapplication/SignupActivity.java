package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    EditText name, username, password;
    Button signup;
    TextView login;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        name = findViewById(R.id.et_name);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_Password);
        signup = findViewById(R.id.Signup_btn);
        login = findViewById(R.id.login_tv);
        firebaseAuth =FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etname = name.getText().toString().trim();
                String etusername = username.getText().toString().trim();
                String etpass = password.getText().toString().trim();
                if (etname .isEmpty()) {
                    name.setError("Required");
                    return;
                }
                if (etusername  .isEmpty()) {
                    username.setError("Required");
                    return;
                }
                if (etpass .isEmpty()) {
                  password .setError("Required");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(etusername).matches()) {
                    username .setError("Invalid Email Format");
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(etusername,etpass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent i=new Intent(SignupActivity.this, MainActivity .class);
                                    startActivity(i);
                                    finish();
                                    Toast.makeText(SignupActivity.this,"Thanks for being Awesome!",Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    Toast.makeText(SignupActivity.this,"Error:"+task.getException() ,Toast.LENGTH_LONG ).show();
                                    task.getException();

                                }
                            }
                        });
            }
        });
         signup.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v) {
                 Intent i=new Intent(SignupActivity .this, MainActivity .class);
                 startActivity(i);

             }
         });


    }
}