package com.example.myapplication;

import static android.app.ProgressDialog.show;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText et1, et2;
    Button bt1;
    TextView tv1,tv2;
    CheckBox  checkBox;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et1 = findViewById(R.id.username);
        et2 = findViewById(R.id.Password);
        bt1 = findViewById(R.id.login_btn);
        tv1 = findViewById(R.id.signup_tv);
        tv2=findViewById(R.id.terms);
        checkBox =findViewById(R.id.checkbox);

        mAuth = FirebaseAuth.getInstance();

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iv = new Intent(LoginActivity.this, SignupActivity .class);
                startActivity(iv);

            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iv=new Intent(LoginActivity.this, termsActivity.class);
                startActivity(iv);


            }
        });


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et1.getText().toString().trim();
                String Password = et2.getText().toString().trim();
                if (username.isEmpty()) {
                    et1.setError("Required");
                    return;

                }

                if (Password.isEmpty()) {
                    et2.setError("Required");
                    return;

                }
                if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                    et1.setError("Invalid Email Format");
                    return;
                }
                if(!checkBox.isChecked()){
                    tv2.setError("Accept");
                    return;
                }

                mAuth.signInWithEmailAndPassword(username, Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent iv = new Intent(LoginActivity.this,MainActivity .class);
                                    startActivity(iv);
                                    finish();

                                }
                                else {
                                    Toast.makeText(LoginActivity.this, "Email or Password incorrect", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
        });
    }
}










