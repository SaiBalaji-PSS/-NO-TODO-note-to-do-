package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<AuthResult> {
    Button btn;
    EditText username,pass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.button);
        mAuth=FirebaseAuth.getInstance();
        username=(EditText)findViewById(R.id.editText);
        pass=(EditText)findViewById(R.id.editText2);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        loginuser();

    }

    void loginuser()
    {
        String email = username.getText().toString().trim();
        String password = pass.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this);

    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(!task.isSuccessful())
        {
            Toast.makeText(this, "An error has occured", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
    }
}
