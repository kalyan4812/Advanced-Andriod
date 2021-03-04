package com.example.advancedandriod_1.ALARAM_MANAGER;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.advancedandriod_1.R;

import java.text.DateFormat;
import java.util.Calendar;

public class Alaram extends AppCompatActivity {
TextView ed;
    Calendar cd;
  static   NotificationManager   notificationManager;

//private  final String TAG="TIME CHECK";
MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alaram);
        ed=findViewById(R.id.time);

        notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void timepick(View view) {
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                 cd = Calendar.getInstance();
                cd.set(Calendar.HOUR_OF_DAY,hourOfDay);
                cd.set(Calendar.MINUTE,minute);
                cd.set(Calendar.SECOND,0);
                ed.setText("ALARAM @ "+DateFormat.getTimeInstance().format(cd.getTime()));
            }
        };
        final Calendar c = Calendar.getInstance();
        int nHour = c.get(Calendar.HOUR_OF_DAY);
        int nMinute = c.get(Calendar.MINUTE);
// using calender class make us to put default date as current time
        TimePickerDialog tp = new TimePickerDialog(this, listener, nHour, nMinute, false);
        tp.show();
    }
    AlarmManager am;
    public void start(View view) {
        Intent i=new Intent(getApplicationContext(), AlaramBroadcast.class);

        PendingIntent pi=PendingIntent.getBroadcast(getApplicationContext(),0,i,0);
if(cd.before(Calendar.getInstance())){
    cd.add(Calendar.DATE,1);
}
         am=(AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Toast.makeText(getApplicationContext(),SystemClock.elapsedRealtime()+"",Toast.LENGTH_LONG).show();
     am.setExact(AlarmManager.RTC_WAKEUP,cd.getTimeInMillis(),pi);

    }

    public void stopalaram(View view) {
        Intent i=new Intent(getApplicationContext(),AlaramBroadcast.class);

        PendingIntent pi=PendingIntent.getBroadcast(getApplicationContext(),0,i,0);

    //   AlarmManager am=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert am != null;
        am.cancel(pi);
     //  mp.release();
        ed.setText("ALARAM WAS CANCELLED");
    }

}
