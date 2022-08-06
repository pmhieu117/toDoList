package com.example.todolist.models;

import java.io.Serializable;
import java.util.Date;

public class TaskModel implements Serializable {
    private int taskid,status;
    private String username,title,location;
    private Date createDate,endDate;

    public TaskModel() {
    }

    public TaskModel(int taskid, int status, String username, String title, String location, Date createDate, Date endDate) {
        this.taskid = taskid;
        this.status = status;
        this.username = username;
        this.title = title;
        this.location = location;
        this.createDate = createDate;
        this.endDate = endDate;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "taskid=" + taskid +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", createDate=" + createDate +
                ", endDate=" + endDate +
                '}';
    }
}
