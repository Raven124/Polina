package com.example.testr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    public static Intent intentt;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("TAG", intentt.getStringExtra("Name"));
        NotifHelper notifHelper=new NotifHelper(context);

        NotificationCompat.Builder nb = notifHelper.getChanelNotifi(intentt.getStringExtra("Name"),
                "Скоро конец дела: "+intentt.getStringExtra("DateF"));
        notifHelper.getNotificationManager().notify(1,nb.build());
    }

}