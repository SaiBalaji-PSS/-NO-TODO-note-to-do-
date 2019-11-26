package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    EditText notesedittext;
    sqllitemanager  obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        obj = new sqllitemanager(this);
        notesedittext=(EditText)findViewById(R.id.editText5);
    }


    public void save(View view) {


        SQLiteDatabase db = obj.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(sqllitemanager.COL2,notesedittext.getText().toString());
        long status = db.insert(sqllitemanager.TABLE_NAME,null,con);

        if(status!=-1)
        {
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,HomeActivity.class);
            startActivity(i);
        }




    }
}
