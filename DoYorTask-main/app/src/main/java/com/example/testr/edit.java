package com.example.testr;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.telephony.AvailableNetworkInfo.PRIORITY_HIGH;

public class edit extends AppCompatActivity {
    public static final String EXTRA_REPLY =
            "REPLY";
    public String MY1;
    public String MY2;
    private Float fl;
    private static String CHANNEL_ID = "MY channel";
    private static int NOTIFY_ID;
    private EditText mEditWordView;
    private NotificationManager notificationManager;
    private TextView currentDateTime;
    private TextView currentDateTime1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mEditWordView = findViewById(R.id.edit_word);
        currentDateTime = findViewById(R.id.textView5);
        currentDateTime1 = findViewById(R.id.textView6);
        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        final Button button = findViewById(R.id.button_save);
        setInitialDateTime();

        ratingBar.setNumStars(5);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar.setRating(rating);
                fl=ratingBar.getRating();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(mEditWordView.getText()==null || ratingBar.getRating()==0f||currentDateTime.getText()==null||currentDateTime1.getText()==null){
                    Toast.makeText(getApplicationContext(), "Проверьте заполненность полей", Toast.LENGTH_SHORT).show();
                    Log.i("onClick: ","true");
                }
                else {
                    Log.i("onClick: ", currentDateTime.getText().toString() + " " + currentDateTime1.getText().toString());
                    if(currentDateTime.getText().equals(currentDateTime1.getText())){
                        Toast.makeText(getApplicationContext(), "Ваши дата или время совпадают", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Log.i("onClick: ","false");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("NameDelo",mEditWordView.getText().toString());
                        Log.i("onClick: ",mEditWordView.getText().toString());
                        intent.putExtra("RatingDelo",ratingBar.getRating());
                        intent.putExtra("StartDelo",currentDateTime.getText().toString());
                        intent.putExtra("FinishDelo",currentDateTime1.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                }
//                float f1=ratingBar.getRating();
//                Information_InGson information_inGson = new Information_InGson();
//                information_inGson.Name=mEditWordView.getText().toString();
//                information_inGson.nDate1=MY1;
//                information_inGson.nDate2=MY2;
//                information_inGson.nRating=f1;
//                GsonBuilder builder = new GsonBuilder();
//                Gson gson = builder.create();
//                gson.toJson(MY1);
//                Log.i("GSON", gson.toJson(information_inGson));
//                Intent replyIntent = new Intent();
//                if (TextUtils.isEmpty(mEditWordView.getText())) {
//                    setResult(RESULT_CANCELED, replyIntent);
//                } else {
//
//                    String word = gson.toJson(information_inGson);
//                    replyIntent.putExtra(EXTRA_REPLY, word);
//                    setResult(RESULT_OK, replyIntent);
//                }
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                NotificationCompat.Builder notificationBuilder =
//                        new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
//                                .setAutoCancel(false)
//                                .setSmallIcon(R.mipmap.ic_launcher)
//                                .setWhen(System.currentTimeMillis())
//                                .setContentIntent(pendingIntent)
//                                .setContentTitle(mEditWordView.getText())
//                                .setContentText(Float.toString(fl)+" "+MY1+" "+MY2)
//                                .setPriority(PRIORITY_HIGH);
//
//                createChannelIfNeeded(notificationManager);
//                notificationManager.notify(NOTIFY_ID, notificationBuilder.build());
//                finish();
            }
        });
    }
//    public static void createChannelIfNeeded(NotificationManager manager) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
//            manager.createNotificationChannel(notificationChannel);
//        }
//    }
    Calendar dateAndTime=Calendar.getInstance();
    Calendar dateAndTime2=Calendar.getInstance();

    public void setDate(View v) {
        new DatePickerDialog(this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        new TimePickerDialog(this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }
    // установка начальных даты и времени
    private void setInitialDateTime() {


        MY1= DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME);
        currentDateTime.setText(MY1);

        MY2=DateUtils.formatDateTime(this,
                dateAndTime2.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME);
        currentDateTime1.setText(MY2);
    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };
    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
    ///
    public void setDate1(View v) {
        new DatePickerDialog(this, d1,
                dateAndTime2.get(Calendar.YEAR),
                dateAndTime2.get(Calendar.MONTH),
                dateAndTime2.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // отображаем диалоговое окно для выбора времени
    public void setTime1(View v) {
        new TimePickerDialog(this, t1,
                dateAndTime2.get(Calendar.HOUR_OF_DAY),
                dateAndTime2.get(Calendar.MINUTE), true)
                .show();
    }
    TimePickerDialog.OnTimeSetListener t1=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime2.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime2.set(Calendar.MINUTE, minute);
            setInitialDateTime1();
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d1=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime2.set(Calendar.YEAR, year);
            dateAndTime2.set(Calendar.MONTH, monthOfYear);
            dateAndTime2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime1();
        }
    };
    private void setInitialDateTime1() {

        TextView currentDateTime=findViewById(R.id.textView6);
        MY2=DateUtils.formatDateTime(this,
                dateAndTime2.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME);
        currentDateTime.setText(MY2);
    }
}