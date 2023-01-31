package com.example.testr;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.core.app.NotificationCompat;

public class NotifHelper extends ContextWrapper {
    public static String chanel1Id="Chanel1ID";
    public static String chanel1Name="Chanel1";
    private NotificationManager notificationManager;
    public NotifHelper(Context base) {
        super(base);
        crearechanels();
    }
    public void crearechanels(){
        NotificationChannel channel1 = new NotificationChannel(chanel1Id,chanel1Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.colorPrimaryLight);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getNotificationManager().createNotificationChannel(channel1);
    }
    public NotificationManager getNotificationManager(){
        if(notificationManager == null){
            notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }
    public NotificationCompat.Builder getChanelNotifi(String title,String message){
        return  new NotificationCompat.Builder(getApplicationContext(),chanel1Id)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_one);
    }
}
