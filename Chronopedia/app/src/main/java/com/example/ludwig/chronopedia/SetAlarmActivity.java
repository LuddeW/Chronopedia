package com.example.ludwig.chronopedia;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TimePicker;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import android.media.Ringtone;
import android.media.RingtoneManager;

import Handlers.AlarmHandler;
import Models.Alarm;

public class SetAlarmActivity extends Activity {

    DatePicker datePicker;
    TimePicker timePicker;
    Button btnSetAlarm;
    Button btnStopAlarm;
    TextView info;
    AlarmHandler alarmHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        info = findViewById(R.id.info);
        datePicker =  findViewById(R.id.datePicker);
        timePicker =  findViewById(R.id.timePicker);
        Calendar now = Calendar.getInstance();
        datePicker.init(now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);
        timePicker.setIs24HourView(true);
        timePicker.setHour(now.get(Calendar.HOUR_OF_DAY));
        timePicker.setMinute(now.get(Calendar.MINUTE));
        btnSetAlarm = findViewById(R.id.startAlarm);
        btnStopAlarm = findViewById(R.id.stopAlarm);
        alarmHandler = new AlarmHandler(getApplicationContext());

        btnSetAlarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar current = Calendar.getInstance();

                Calendar cal = Calendar.getInstance();
                cal.set(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getHour(),
                        timePicker.getMinute(),
                        00);
                if (cal.compareTo(current) <= 0) {
                    Toast.makeText(getApplicationContext(),
                            "Invalid Date/Time",
                            Toast.LENGTH_LONG).show();
                } else {
                    alarmHandler.setAlarm(cal);
                }
            }
        });
        btnStopAlarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calendar cal = Calendar.getInstance();
                //alarmHandler.stopAlarm(cal);
            }
        });
    }
}
