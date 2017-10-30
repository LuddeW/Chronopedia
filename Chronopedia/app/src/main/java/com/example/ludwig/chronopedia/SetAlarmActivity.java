package com.example.ludwig.chronopedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.Calendar;


import Handlers.AlarmHandler;

public class SetAlarmActivity extends Activity {

    private PendingIntent pendingIntent;
    public int minute;
    public int hour;
    public int amPm;
    public int weekDay;
    public int repeatIntervalMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        Intent alarmIntent = new Intent(SetAlarmActivity.this, AlarmHandler.class);
        pendingIntent = PendingIntent.getBroadcast(SetAlarmActivity.this, 0, alarmIntent, 0);

        findViewById(R.id.startAlarm).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        findViewById(R.id.stopAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60;

        // Set time (and date)
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.AM_PM, amPm);
        calendar.set(Calendar.DAY_OF_WEEK, weekDay);

        // Repeat interval
        manager.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                interval * repeatIntervalMinutes, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }
}
