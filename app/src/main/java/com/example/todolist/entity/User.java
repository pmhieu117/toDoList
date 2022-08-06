package com.example.todolist.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "user")
public class User {
    @PrimaryKey
    @NonNull
    public String username;

    @ColumnInfo
    public String password;

    @ColumnInfo
    public String fullname;

    @ColumnInfo
    public String email;

    @ColumnInfo
    public int gender; //1: Nam, 0: Nu

    @ColumnInfo
    public String birth;

}
