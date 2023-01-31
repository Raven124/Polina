package com.example.testr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.testr.helper.SimpleItemTouchHelperCallback;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Delo> delos =new ArrayList<>();
    List<Delo> buf=new ArrayList<>();
    List<String> listS = new ArrayList<>();
    List<Float> listF =new ArrayList<>();
    ArrayList<Date> parsingDate=new ArrayList<>();
    Date date=new Date();
    public RecyclerAdapter adapter1;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private ItemTouchHelper mItemTouchHelper;
    private NotifHelper notifHelper;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        setContentView(R.layout.activity_main);
        notifHelper=new NotifHelper(this);
        setInitialData();
        RecyclerView recyclerView =findViewById(R.id.RecyclerView);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        adapter1=new RecyclerAdapter(this,delos);
        recyclerView.setAdapter(adapter1);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter1);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        ImageButton imageButton= findViewById(R.id.imageButton);
        Spinner spinner = findViewById(R.id.spinner);
        String selected = spinner.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT).show();
        imageButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(pendingIntent != null){
                    cancelAlarm();
                    pendingIntent = null;
                }
                else
                {
                    Intent intent1 = new Intent(MainActivity.this, edit.class);
                    startActivityForResult(intent1,NEW_WORD_ACTIVITY_REQUEST_CODE);
                }

            }
        });//обработка кнопки
        final ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.Sortby, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.Sortby);
//                Toast toast = Toast.makeText(getApplicationContext(),
//                        "Ваш выбор: " + choose[selectedItemPosition], Toast.LENGTH_SHORT);
//                toast.show();

                switch(choose[selectedItemPosition]) {
                    case "Нет сортировки":
                        break;
                    case "По имени": {
                        buf.add(new Delo("1", "1", "1", 0f));
                        for (int i = 0; i < delos.size(); i++) {
                            listS.add(delos.get(i).getNameDelo());
                        }
                        Collections.sort(listS);
                        for (int i = 0; i < listS.size(); i++) {
                            for (int j = 0; j < delos.size(); j++) {
                                if (listS.get(i) == delos.get(j).getNameDelo()) {
                                    buf.set(0, delos.get(i));
                                    delos.set(i, delos.get(j));
                                    delos.set(j, buf.get(0));
                                }
                            }
                        }
                        listS.clear();
                        buf.clear();
                        adapter1.notifyDataSetChanged();
                    }
                    break;//sortname
                    case "По рейтингу":
                    {
                        buf.add(new Delo("1", "1", "1", 0f));
                        for (int i = 0; i < delos.size(); i++) {
                            listF.add(delos.get(i).getRatingDelo());
                        }
                        Collections.sort(listF);
                        for (int i = 0; i < listF.size(); i++) {
                            for (int j = 0; j < delos.size(); j++) {
                                if (listF.get(i) == delos.get(j).getRatingDelo()) {
                                    buf.set(0, delos.get(i));
                                    delos.set(i, delos.get(j));
                                    delos.set(j, buf.get(0));
                                }
                            }
                        }
                        adapter1.notifyDataSetChanged();
                        listF.clear();
                        buf.clear();
                    }
                    break;//сортировка по рейтингу
                    case "По стартовой дате": {
                        buf.add(new Delo ("1", "1","1",0f ));
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy");
                        String str;
                        for (int i = 0; i < delos.size(); i++) {
                            str=delos.get(i).getStartDelo();
                            try {
                                parsingDate.add(simpleDateFormat.parse(str));
                            }catch (ParseException e) {
                                Toast toast1 = Toast.makeText(getApplicationContext(),
                                        "Что-то пошло не так", Toast.LENGTH_SHORT);
                                toast1.show();
                            }
                        }
                        Collections.sort(parsingDate);
                        for (int i = 0; i < parsingDate.size(); i++) {
                            Log.i("Curent",parsingDate.get(i).toString()+" "+i);
                        }
                        for (int i = 0; i < parsingDate.size(); i++) {
                            for (int j = i; j < delos.size(); j++) {
                                str=delos.get(j).getStartDelo();
                                try {
                                    date=simpleDateFormat.parse(str);
                                    Log.i("a",date.toString());
                                }catch (ParseException e) {
                                    Toast toast1 = Toast.makeText(getApplicationContext(),
                                            "Что-то пошло не так", Toast.LENGTH_SHORT);
                                    toast1.show();
                                }
                                if(parsingDate.get(i).getTime()==date.getTime()){
                                    Log.i("ifdoing","Yes");
                                    buf.set(0,delos.get(i));
                                    delos.set(i,delos.get(j));
                                    delos.set(j,buf.get(0));
                                    break;
                                }
                                Log.i("DelosI",delos.get(i).getStartDelo());
                                Log.i("Delosj",delos.get(j).getStartDelo());
                            }
                        }
                        adapter1.notifyDataSetChanged();
                        parsingDate.clear();
                        buf.clear();
                    }//sortdate
                    break;
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

// Вызываем адаптер
        spinner.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }//создание меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, setting.class);

        int id = item.getItemId();
        switch(id){
            case R.id.action_settings :
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }//выбор элемента меню
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY){

        return true;
    }
    private void setInitialData(){
        delos.add(new Delo ("e", "11.11.20","13.12.20",1f ));//1
        delos.add(new Delo ("f", "11.11.20","13.12.20",2f ));//2
        delos.add(new Delo ("g", "11.12.20", "15.12.20",3f));//3
        delos.add(new Delo ("h", "13.11.20", "13.12.20",4f));//4
        delos.add(new Delo ("z", "13.12.20","20.12.20",5f ));//5
        delos.add(new Delo ("i", "11.11.20","13.12.20",0.5f ));//6
        delos.add(new Delo ("d", "11.11.20","13.12.20",1.5f ));//7
        delos.add(new Delo ("c", "11.12.20", "15.12.20",2.5f));//8
        delos.add(new Delo ("b", "13.11.20", "13.12.20",3.5f));//9
        delos.add(new Delo ("a", "13.12.20","20.12.20",4.5f ));//10
    }
    public void onActivityResult(int requestCode, int resultCode, Intent arguments) {
        super.onActivityResult(requestCode, resultCode, arguments);

        Log.i("onActivityResult: ", "String.valueOf(arguments!=null)");
        if(arguments!=null) {
            delos.add(new Delo(arguments.getStringExtra("NameDelo"),arguments.getStringExtra("StartDelo").toString(),arguments.getStringExtra("FinishDelo").toString(),arguments.getFloatExtra("RatingDelo",0f)));
            Log.i("onActivityResult: ", arguments.getStringExtra("NameDelo"));
            adapter1.notifyDataSetChanged();
//            SendOnChanel(arguments.getStringExtra("NameDelo"),arguments.getStringExtra("StartDelo"));
            StartAlarm(arguments.getStringExtra("NameDelo"),
                    arguments.getStringExtra("StartDelo"),
                    arguments.getStringExtra("FinishDelo"),
                    arguments.getFloatExtra("RatingDelo",0f)
            );
        }
    }
//    public void SendOnChanel(String title,String message){
//        NotificationCompat.Builder nb = notifHelper.getChanelNotifi(title,message);
//        notifHelper.getNotificationManager().notify(1,nb.build());
//    }


    public void StartAlarm(String name,String dates,String datef,Float rating){

        Intent intentA=new Intent(this,AlertReceiver.class);
        Log.e("TAG42", name);
        intentA.putExtra("Name",name);
        intentA.putExtra("DateS",dates);
        intentA.putExtra("DateF",datef);
        intentA.putExtra("Rating",rating);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMMM d, yyyy, h:m a");
        Date date1=null;
        try {
            date1=simpleDateFormat.parse(datef);
            Log.i("StartAlarm: ", date1.toString());
        } catch (ParseException e) {
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "Что-то пошло не так", Toast.LENGTH_SHORT);
            toast1.show();
        }

        AlertReceiver.intentt = intentA;

        pendingIntent = PendingIntent.getBroadcast(this,1,intentA,0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + 10000,
                pendingIntent);
    }
    public void cancelAlarm(){
        Log.i("cancelAlarm: ", "cancelAlarm: ");
        alarmManager.cancel(pendingIntent);
    }
}
