package com.example.todolist.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.todolist.R;

import com.example.todolist.models.TaskModel;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
//INSERT INTO task(username,createdate,title,enddate,location,status) values('hh','27/07/2022','gg','31/07/2022','Ha Dong', 0);

import java.text.SimpleDateFormat;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ItemClickListener clickListener;
    List<TaskModel> listTask;
    TaskModel task;
    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public TaskAdapter(List<TaskModel> listTask){
        this.listTask= listTask;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_in_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        //holder.title.setText(listTask.get(position).getTitle());
        holder.title.setText(listTask.get(position).getTitle());
        for (TaskModel i : listTask){
            Log.e("Hi",i.toString());
        }
        //TaskModel taskModelTest = listTask.get(position);
        //String endTime=new SimpleDateFormat("hh:mm:ss").format(listTask.get(position).getEndDate());
        String endDate=new SimpleDateFormat("dd/MM/yyyy").format(listTask.get(position).getEndDate());
        String endTime=new SimpleDateFormat("hh:mm:ss").format(listTask.get(position).getEndDate());
        Log.e("dgdfgdf", endTime);
        holder.time.setText(endTime);
        holder.date.setText(endDate);
        holder.location.setText(listTask.get(position).getLocation());
    }

    public TaskModel getTask(int position){
        return listTask.get(position);
    }

    @Override
    public int getItemCount(){
        return listTask.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView username, createdate, title, time, date, location, status;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView1);
            title = itemView.findViewById(R.id.textviewTitle);
            time = itemView.findViewById(R.id.textviewTime);
            date = itemView.findViewById(R.id.textviewDate);
            location = itemView.findViewById(R.id.textviewLocation);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener!= null){
                clickListener.onClick(getAdapterPosition());
            }
        }
    }




}
