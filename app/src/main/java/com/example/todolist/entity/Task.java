package com.example.todolist.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task")
public class Task {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int taskid;

    @ColumnInfo
    @NonNull
    public String username;

    @ColumnInfo
    public String createdate;

    @ColumnInfo
    public String title;

    @ColumnInfo
    public String enddate;

    @ColumnInfo
    public String location;

    @ColumnInfo
    public int status;

    public Task(){
        
    }
    public Task(String username,String createdate,String title,String enddate,String location,int status){
        this.username=username;
        this.createdate=createdate;
        this.title=title;
        this.enddate=enddate;
        this.location=location;
        this.status=status;
    }
}
