package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.todolist.dao.TaskDAO;
import com.example.todolist.database.AppDatabase;
import com.example.todolist.entity.Task;
import com.example.todolist.models.SessionModel;
import com.example.todolist.models.TaskModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class UpdateAndDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    EditText title, location;
    TextView createdate, username, time, date;
    Button btn_save, btn_delete, btn_cancel;
    RadioButton done, nDone;
    RadioGroup radioGroup;
    ImageButton btn_time, btn_date;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TaskModel task=null;
    TaskDAO taskDAO = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "todo-list.db").allowMainThreadQueries().build();
        taskDAO = db.taskDAO();
        setContentView(R.layout.activity_update_and_delete);
        Intent intent = getIntent();
        task = (TaskModel) intent.getSerializableExtra("task");
        done = findViewById(R.id.radio_a);
        nDone = findViewById(R.id.radio_b);
        radioGroup = findViewById(R.id.radio_group);
        btn_time = findViewById(R.id.btn_time);
        btn_date = findViewById(R.id.btn_date);
        btn_date.setOnClickListener(this);
        btn_time.setOnClickListener(this);
        btn_cancel = findViewById(R.id.btn_cancel1);
        username = findViewById(R.id.tvUsername);
        createdate = findViewById(R.id.tvCurrentDate);
        title = findViewById(R.id.edittextTitle);
        location = findViewById(R.id.edittextLocation);
        time = findViewById(R.id.textviewTime);
        date = findViewById(R.id.textviewDate);
        String showCreateDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(task.getCreateDate());
        createdate.setText(showCreateDate);
        title.setText(task.getTitle());
        location.setText(task.getLocation());
        String showEndTime = new SimpleDateFormat("HH:mm:ss").format(task.getEndDate());
        String showEndDate = new SimpleDateFormat("dd/MM/yyyy").format(task.getEndDate());
        Toast.makeText(UpdateAndDeleteActivity.this, showEndTime+" "+showEndDate, Toast.LENGTH_SHORT).show();
        time.setText(showEndTime);
        date.setText(showEndDate);
        username.setText(SessionModel.usename);
        Toast.makeText(this, task.getTitle(), Toast.LENGTH_SHORT).show();
        btn_save = findViewById(R.id.btn_create);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "todo-list.db").allowMainThreadQueries().build();
                TaskDAO taskDAO = db.taskDAO();
                List<Task> tasks=taskDAO.loadAllByIds(new int[]{task.getTaskid()});
                Task task1 = tasks.get(0);
                task1.title=title.getText().toString();
                task1.location=location.getText().toString();
                String t = time.getText().toString();
                String d = date.getText().toString();
                task1.enddate=d+" "+t;
                if(radioGroup.getCheckedRadioButtonId()==done.getId()){
                    task1.status=1;
                }else{
                    task1.status=0;
                }
                taskDAO.update(task1);
                Toast.makeText(UpdateAndDeleteActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                showDetail();

            }
        });
        btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "todo-list.db").allowMainThreadQueries().build();
                TaskDAO taskDAO = db.taskDAO();
                List<Task> tasks=taskDAO.loadAllByIds(new int[]{task.getTaskid()});
                Task task1 = tasks.get(0);
                taskDAO.delete(task1);
                Toast.makeText(UpdateAndDeleteActivity.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(UpdateAndDeleteActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(UpdateAndDeleteActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });

    }
    public void showDetail(){
        List<Task> tasks = taskDAO.loadAllByIds(new int[]{task.getTaskid()});
        Task task1 = tasks.get(0);
        title.setText(task1.title);
        location.setText(task1.location);
        String endate[] = task1.enddate.split(" ");
        String showEndTime = endate[1];
        String showEndDate = endate[0];
        time.setText(showEndTime);
        date.setText(showEndDate);
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
                            monthOfYear+=1;
                            String s = "";
                            if(dayOfMonth<10){
                                s+="0"+dayOfMonth;
                            }else{
                                s+=dayOfMonth;
                            }
                            s+="/";
                            if(monthOfYear<10){
                                s+="0"+monthOfYear;
                            }else {
                                s += monthOfYear;
                            }
                            s+="/";
                            s+=year;
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
                            String s="";
                            if(hourOfDay<10) s+="0"+hourOfDay;
                            else s+=hourOfDay;
                            s+=":";
                            if(minute<10) s+="0"+minute;
                            else s+=minute;
                            s+=":00";
                            time.setText(s);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}