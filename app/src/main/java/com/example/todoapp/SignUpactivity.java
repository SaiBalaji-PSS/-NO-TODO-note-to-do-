package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpactivity extends AppCompatActivity implements OnCompleteListener<AuthResult> {
   EditText user,pass;
   private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upactivity);
        user=(EditText)findViewById(R.id.editText3);
        pass=(EditText)findViewById(R.id.editText4);
        mauth=FirebaseAuth.getInstance();
    }

    public void signup(View view) {

        createnewuser();
    }

    void createnewuser()
    {
        String email=user.getText().toString().trim();
        String password=pass.getText().toString().trim();
        mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this);

    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful())
        {
            Toast.makeText(this, "Account creation success", Toast.LENGTH_SHORT).show();
            user.setText("");
            pass.setText("");
            Intent i = new Intent(this,HomeActivity.class);
            startActivity(i);


        }
        else if(task.getException() instanceof FirebaseAuthUserCollisionException)
        {
            user.setError("This email id is already registered");
            user.requestFocus();
        }

        else
        {
            Toast.makeText(this, "Some error occured", Toast.LENGTH_SHORT).show();
        }
    }
}
