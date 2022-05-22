package com.example.vaccination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Email_authentication extends AppCompatActivity {
    Button mCheck;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_authentication);
        //getSupportActionBar().hide(); //hide action bar
        mCheck = findViewById(R.id.button_check);
        progressBar = findViewById(R.id.progressBar3);
        fAuth = FirebaseAuth.getInstance();

        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // progressBar.setVisibility(View.VISIBLE);
                if(fAuth.getCurrentUser().isEmailVerified()) {
                    Toast.makeText(Email_authentication.this, "User Created.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Email_authentication.this, "Please Verify your Email by click on link, send to your email. ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}