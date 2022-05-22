package com.example.vaccination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class Forgot_Password extends AppCompatActivity {
    EditText mEdittext;
    Button mCancel,mSubmit;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide(); //hide action bar
        setContentView(R.layout.activity_forgot_password);

        mEdittext = findViewById(R.id.edit_text_email_forgot_pass);
        mCancel = findViewById(R.id.button_cancel);
        mSubmit = findViewById(R.id.button_submit);
        progressBar = findViewById(R.id.progressBar4);
        fAuth = FirebaseAuth.getInstance();

        //for submit button
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = mEdittext.getText().toString().trim();
                if(TextUtils.isEmpty(mail)) {
                    mEdittext.setError("Email is required");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        timer = new Timer();
                        Toast.makeText(Forgot_Password.this, "Reset link sent to your Email.", Toast.LENGTH_SHORT).show();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(getApplicationContext(),Login.class));
                                finish();
                            }
                        },3000);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Forgot_Password.this, "Error ! Reset link is not sent. "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //for cancel button
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });
    }
}