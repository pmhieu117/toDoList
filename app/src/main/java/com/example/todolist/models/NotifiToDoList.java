package com.example.todolist.models;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotifiToDoList extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Hi","Notification " + intent.getAction());
        Intent intent1 = new Intent(context,SoundNotifi.class);
        context.startService(intent1);
    }
}
