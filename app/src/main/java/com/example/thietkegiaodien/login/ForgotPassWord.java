package com.example.thietkegiaodien.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thietkegiaodien.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassWord extends AppCompatActivity {
    EditText enterEmail;
    Button reSetPass,gotoLogin;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_word);
        enterEmail = findViewById(R.id.enterEmail);
        reSetPass = findViewById(R.id.resetPass);
        gotoLogin = findViewById(R.id.gotologin);
        auth = FirebaseAuth.getInstance();

        //gotologin
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassWord.this,Login.class));
                finish();
            }
        });

        //resetpass
        reSetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reSetPassWord();
            }
        });
    }

    private void reSetPassWord() {
        String email = enterEmail.getText().toString().trim();
        if (email.isEmpty()) {
            enterEmail.setError("Email is required!");
            enterEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            enterEmail.setError("Please provide valid email!");
            enterEmail.requestFocus();
            return;

        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPassWord.this, "Check your email to reset your password!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(ForgotPassWord.this, "Try again! Something wrong happened! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}