package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {
     private FirebaseAuth mauth;
     TextView cunser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mauth=FirebaseAuth.getInstance();
        cunser=(TextView)findViewById(R.id.textView6);

    }

    @Override
    protected void onStart() {
        super.onStart();
        cunser.setText(mauth.getCurrentUser().getEmail());

    }

    public void signOut(View view) {
        mauth.signOut();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
