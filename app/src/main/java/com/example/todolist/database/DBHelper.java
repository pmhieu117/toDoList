package com.example.todolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTB = "CREATE TABLE user(" +
                "username text primary key not null," +
                "password text," +
                "fullname text," +
                "email text," +
                "gender int," +
                "birth text" +
                ")";
        sqLiteDatabase.execSQL(createUserTB);
        String createTaskTB = "CREATE TABLE task(" +
                "taskid int primary key not null," +
                "username text," +
                "createdate text," +
                "title text," +
                "enddate text," +
                "location text," +
                "status int" +
                ")";
        sqLiteDatabase.execSQL(createTaskTB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
