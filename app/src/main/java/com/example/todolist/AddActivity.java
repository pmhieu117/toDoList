package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

//import com.example.todolist.api.RetrofitClient;
import com.example.todolist.dao.TaskDAO;
import com.example.todolist.database.AppDatabase;
import com.example.todolist.entity.Task;
import com.example.todolist.fragment.HomeFragment;
import com.example.todolist.models.NotifiToDoList;
import com.example.todolist.models.SessionModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    EditText title, location;
    TextView createdate, username, time, date;
    Button btn_create, btn_cancel1;
    ImageButton btn_time, btn_date;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Calendar calendar = Calendar.getInstance();
    private int mYear, mMonth, mDay, mHour, mMinute;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        String currentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(calendar.getTime());
        createdate = findViewById(R.id.tvCurrentDate);
        createdate.setText(currentDate);
        title = findViewById(R.id.edittextTitle);
        time = findViewById(R.id.textviewTime);
        date = findViewById(R.id.textviewDate);
        location = findViewById(R.id.edittextLocation);
        btn_create = findViewById(R.id.btn_create);
        btn_cancel1 = findViewById(R.id.btn_cancel1);
        btn_time = findViewById(R.id.btn_time);
        btn_date = findViewById(R.id.btn_date);

        btn_date.setOnClickListener(this);
        btn_time.setOnClickListener(this);
        //pass data from mainactivity to addactivity
        Intent intent2 = getIntent();
        String name2 = intent2.getStringExtra("username1");
        username = findViewById(R.id.tvUsername);
        username.setText(name2);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intentAlarm = new Intent(AddActivity.this, NotifiToDoList.class);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long currently = Calendar.getInstance().getTimeInMillis();
                String str_username = SessionModel.usename;
                String str_createdate = createdate.getText().toString();
                String str_title = title.getText().toString();
                String str_time = time.getText().toString();
                String str_date = date.getText().toString();
                String enddate = str_date + " " + str_time;
                String str_location = location.getText().toString();
                Date date_endDate = new Date();
                try {
                    date_endDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(enddate);
                    Log.e("DATE", enddate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                pendingIntent = PendingIntent.getBroadcast(AddActivity.this, 0, intentAlarm, PendingIntent.FLAG_ONE_SHOT);
                long passTime = date_endDate.getTime();
                Log.e("DATE", String.valueOf(passTime));
                alarmManager.set(AlarmManager.RTC_WAKEUP, passTime, pendingIntent);
                Task newTask = new Task(str_username, str_createdate, str_title, enddate, str_location, 0);
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "todo-list.db").allowMainThreadQueries().build();
                TaskDAO taskDAO = db.taskDAO();
                taskDAO.insertAll(newTask);
                Toast.makeText(AddActivity.this, "Create Successfullyt", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == btn_date) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            monthOfYear += 1;
                            String s = "";
                            if (dayOfMonth < 10) {
                                s += "0" + dayOfMonth;
                            } else {
                                s += dayOfMonth;
                            }
                            s += "/";
                            if (monthOfYear < 10) {
                                s += "0" + monthOfYear;
                            } else {
                                s += monthOfYear;
                            }
                            s += "/";
                            s += year;
                            date.setText(s);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btn_time) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String s = "";
                            if (hourOfDay < 10) s += "0" + hourOfDay;
                            else s += hourOfDay;
                            s += ":";
                            if (minute < 10) s += "0" + minute;
                            else s += minute;

                            s += ":00";

                            time.setText(s);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}