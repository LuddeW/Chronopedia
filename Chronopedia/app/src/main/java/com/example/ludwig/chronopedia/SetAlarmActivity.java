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
    int alarmInterval = 10;
    PendingIntent pendingIntent;
    Intent intent;
    AlarmManager alarmManager;
    DatabaseHandler handler = new DatabaseHandler(this);
    final static int RQS_1 = 1;

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
                    setAlarm(cal);
                }
            }
        });
        btnStopAlarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                stopAlarm(cal);
            }
        });
    }

    private void setAlarm(Calendar targetCal) {
        Toast.makeText(this,
                "\n\n***\n"
                        + "Alarm has been set "
                        + targetCal.getTime()
                        + "\n" + "***\n",
                Toast.LENGTH_SHORT).show();

        intent = new Intent(getBaseContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), alarmInterval, pendingIntent);

        //Sending to database FUNKAR INTE behöver rätt tid och datum
//        Alarm alarm = new Alarm();
//        alarm.setDate(targetCal.getTime().getDay(), targetCal.getTime().getHours(), targetCal.getTime().getMinutes());
//        handler.insertAlarm(alarm);


    }
    private void stopAlarm(Calendar targetCal){

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if(alarmManager!=null) {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(this,"\n\n***\n"
                            + "The alarm set to "
                            + targetCal.getTime()
                            + "\n" + "has been cancelled." + "***\n",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
