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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.dao.UserDAO;
import com.example.todolist.database.AppDatabase;
import com.example.todolist.database.DBHelper;
import com.example.todolist.entity.User;
import com.example.todolist.models.SessionModel;
import com.example.todolist.models.UserModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText eUsername, ePassword;
    Button btn_login;
    TextView txt_signup;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper =new DBHelper(this,"todo-list.db",null,1);
        eUsername =findViewById(R.id.username);
        ePassword=findViewById(R.id.password);
        btn_login =findViewById(R.id.btn_login);
        txt_signup=findViewById(R.id.txt_signup);
        checkBox = findViewById(R.id.checkbox);
            txt_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                }
            });

            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str_username = eUsername.getText().toString();
                    String str_password = ePassword.getText().toString();
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
                            birth = new SimpleDateFormat("dd/mm/yyyy").parse(c.getString(5));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        userModel = new UserModel(username, password, fullname, email, gender, birth);
                    }
                    if (userModel != null) {
                        if(checkBox.isChecked()){
                            Toast.makeText(LoginActivity.this, "checkbox is checked", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = getSharedPreferences("nick",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username",str_username);
                            editor.putString("password",str_password);
                            editor.commit();
                        }
                        SessionModel.usename = str_username;
                        SessionModel.password = str_password;
                        Toast.makeText(LoginActivity.this, "Login Successfull!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username", userModel.getFullname());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong username or password!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }
}