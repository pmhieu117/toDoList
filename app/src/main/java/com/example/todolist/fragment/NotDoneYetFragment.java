package com.example.todolist.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.UpdateAndDeleteActivity;
import com.example.todolist.adapter.ItemClickListener;
import com.example.todolist.adapter.TaskAdapter;
import com.example.todolist.database.DBHelper;
import com.example.todolist.models.SessionModel;
import com.example.todolist.models.TaskModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class NotDoneYetFragment extends Fragment {
    TaskAdapter adapter;
    RecyclerView recyclerView;
    List<TaskModel> taskList = new ArrayList<>();
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notdoneyet, container, false);
        dbHelper = new DBHelper(getActivity(),"todo-list.db",null,1);;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(dbHelper.getDatabaseName()==null){
            Toast.makeText(getActivity(), "NULL", Toast.LENGTH_SHORT).show();
        }
        String query="SELECT * FROM task WHERE username = ?";
        db = dbHelper.getReadableDatabase();
        Cursor c =db.rawQuery(query,new String[]{SessionModel.usename});
        while(c.moveToNext()){
            Date date_createtask = new Date();
            Date date_endtask=new Date();
            try {
                Log.e("HomeFragment",c.getString(2));
                date_createtask = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(c.getString(2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                Log.e("HomeFragment",c.getString(4));
                date_endtask = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(c.getString(4));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(Calendar.getInstance().getTime().after(date_endtask)&& c.getInt(6)==0){
                TaskModel taskModel = new TaskModel(c.getInt(0),c.getInt(6),c.getString(1),c.getString(3),c.getString(5),
                        date_createtask,date_endtask);
                taskList.add(taskModel);
            }
        }
        recyclerView = view.findViewById(R.id.notdoneyet_recyclerview);
        adapter = new TaskAdapter(taskList);
        adapter.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), "" + position,
                        Toast.LENGTH_SHORT).show();
                TaskModel task = adapter.getTask(position);
                Intent intent = new Intent(getActivity(),
                        UpdateAndDeleteActivity.class);
                intent.putExtra("task", task);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }
}