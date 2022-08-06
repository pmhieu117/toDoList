package com.example.todolist.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todolist.dao.TaskDAO;
import com.example.todolist.dao.UserDAO;
import com.example.todolist.entity.Task;
import com.example.todolist.entity.User;

@Database(entities = {User.class,Task.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract TaskDAO taskDAO();
}
