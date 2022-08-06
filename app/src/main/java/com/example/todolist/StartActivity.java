package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.todolist.dao.TaskDAO;
import com.example.todolist.dao.UserDAO;
import com.example.todolist.database.AppDatabase;
import com.example.todolist.database.DBHelper;
import com.example.todolist.entity.Task;
import com.example.todolist.entity.User;
import com.example.todolist.models.SessionModel;
import com.example.todolist.models.UserModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    Button btn_login, btn_register;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        dbHelper =new DBHelper(this,"todo-list.db",null,1);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, RegisterActivity.class));
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("nick",MODE_PRIVATE);
        if(!sharedPreferences.getString("username","").equals("")){
            String str_username = sharedPreferences.getString("username","");
            String str_password = sharedPreferences.getString("password","");
            //                Toast.makeText(LoginActivity.this, str_password, Toast.LENGTH_SHORT).show();
            UserModel userModel = null;
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            db = dbHelper.getReadableDatabase();
            Cursor c = db.rawQuery(query, new String[]{str_username, str_password});
            if (c.moveToNext()) {
                String username = c.getString(0);
                String password = c.getString(1);
                String fullname = c.getString(2);
                String email = c.getString(3);
                int gender = c.getInt(4);
                Date birth = new Date();
                try {
                    birth = new SimpleDateFormat("dd/MM/yyyy").parse(c.getString(5));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                userModel = new UserModel(username, password, fullname, email, gender, birth);
            }
            SessionModel.usename = str_username;
            SessionModel.password = str_password;
            Toast.makeText(StartActivity.this, "Login Successfull!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            intent.putExtra("username", userModel.getFullname());
            startActivity(intent);
            finish();
        }

    }
}