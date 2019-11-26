package com.example.todoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class sqllitemanager extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="data.db";
    public static final String TABLE_NAME="Notes";
    public static final String COL1="id";
    public static final String COL2="note";








    public sqllitemanager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase DB = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Notes (id integer primary key autoincrement ,note text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists Notes");
        onCreate(db);

    }
}
