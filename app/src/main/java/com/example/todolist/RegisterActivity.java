package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.dao.UserDAO;
import com.example.todolist.database.AppDatabase;
import com.example.todolist.entity.User;
import com.example.todolist.models.UserModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.example.todolist.api.RetrofitClient;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, fullname, email,birth,gender;
    Button btn_register;
    TextView txt_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        fullname=findViewById(R.id.fullname);
        password=findViewById(R.id.password);
        birth=findViewById(R.id.birth);
        gender = findViewById(R.id.gender);
        btn_register=findViewById(R.id.btn_register);
        txt_login=findViewById(R.id.txt_login);
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_username=username.getText().toString();
                String str_fullname=fullname.getText().toString();
                String str_email=email.getText().toString();
                String str_password=password.getText().toString();
                String str_birth = birth.getText().toString();
                String str_gender = gender.getText().toString();
                int int_gender = str_gender.equals("Nam")?1:0;
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "todo-list.db").allowMainThreadQueries().build();
                UserDAO userDao = db.userDAO();
                User user = new User();
                user.username=str_username;
                user.password=str_password;
                user.fullname=str_fullname;
                user.email=str_email;
                user.birth=str_birth;
                user.gender=int_gender;
                userDao.insertAll(user);
                Toast.makeText(RegisterActivity.this, "Register Successfull!",
                        Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}