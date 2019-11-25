package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<AuthResult> {
    Button btn;
    EditText username,pass;
    TextView newuser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.button);
        mAuth=FirebaseAuth.getInstance();
        username=(EditText)findViewById(R.id.editText);
        pass=(EditText)findViewById(R.id.editText2);
        newuser=(TextView)findViewById(R.id.textView3);
        btn.setOnClickListener(this);
        newuser.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentuser = mAuth.getCurrentUser();
        if(currentuser!=null)
        {
            Intent i = new Intent(this,HomeActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button:
            {
                loginuser();
                break;
            }

            case R.id.textView3:
            {
                signupnewuser();
                break;
            }



        }


    }

    private void signupnewuser()
    {
        Intent intent = new Intent(this,SignUpactivity.class);
        startActivity(intent);


    }

  private  void loginuser()
    {
        String email = username.getText().toString().trim();
        String password = pass.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            username.setError("Enter a valid email address");
            username.requestFocus();
            return;
        }
        if(password.length()<8)
        {
            pass.setError("Enter a password of length 8 or above");
            return;
        }

       mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this);

    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(!task.isSuccessful())
        {
            Toast.makeText(this,  task.getException().toString(), Toast.LENGTH_SHORT).show();
        }
        else if(task.getException() instanceof FirebaseAuthUserCollisionException)
        {
          username.setError("This email id is already registered");
          username.requestFocus();
        }
        else
        {


            Intent i2 = new Intent(this,HomeActivity.class);
            startActivity(i2);

        }
    }
}
