package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
   TextView savednotes;

    sqllitemanager obj;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        obj=new sqllitemanager(this);
        savednotes= (TextView)findViewById(R.id.textView7);

        savednotes.setMovementMethod(new ScrollingMovementMethod());



      /*  AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Info");
        mAuth=FirebaseAuth.getInstance();

        builder.setMessage("Welcome::"+""+mAuth.getCurrentUser().getEmail());
        builder.setPositiveButton("Ok",null);

        AlertDialog dialog = builder.create();
        dialog.show();*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        savednotes.setText("");

        SQLiteDatabase db = obj.getReadableDatabase();
        Cursor c= db.rawQuery("select * from "+sqllitemanager.TABLE_NAME,null);

        if(c.getCount()==0)
        {
            savednotes.setText("");
            return;
        }

        StringBuffer buffer = new StringBuffer();


        while(c.moveToNext())
        {


            buffer.append("NOTE::"+c.getString(1)+"\n");


        }






        savednotes.setText(savednotes.getText().toString()+buffer+"\n");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.one:
            {
                Intent s= new Intent(this,Settings.class);
                startActivity(s);
                break;


            }
            case R.id.two:
            {
               Intent s2 = new Intent(this,About.class);
               startActivity(s2);
            }
        }
        return true;
    }

    public void addnote(View view) {
          Intent newnote = new Intent(this,NoteActivity.class);
          startActivity(newnote);

    }

    public void clear(View view) {
        SQLiteDatabase db = obj.getWritableDatabase();
        db.execSQL("delete from Notes");

        savednotes.setText("");
    }
}
