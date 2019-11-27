package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void go(View view) {

        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/SaiBalaji22/-NO-TODO-note-to-do-"));
        startActivity(i);
    }
}
