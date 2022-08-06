package com.example.todolist.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist.entity.Task;

import java.util.List;

@Dao
public interface TaskDAO {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task WHERE taskid IN (:taskIds)")
    List<Task> loadAllByIds(int[] taskIds);


    @Insert
    void insertAll(Task... tasks);

    @Delete
    void delete(Task task);

    @Update
    void update(Task task);
}
