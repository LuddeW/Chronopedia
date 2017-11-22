package Handlers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.ludwig.chronopedia.AlarmReceiver;
import com.example.ludwig.chronopedia.DatabaseHandler;
import com.example.ludwig.chronopedia.SetAlarmActivity;

import java.util.Calendar;

import Models.Alarm;

/**
 * Created by Ludwig on 2017-10-24.
 */

public class AlarmHandler {
    Context context;
    PendingIntent pendingIntent;
    Intent intent;
    DatabaseHandler handler;
    int alarmInterval = 10;
    AlarmManager alarmManager;

    public AlarmHandler(Context context){
        this.context = context;
        handler = new DatabaseHandler(context);
    }
    public void setAlarm(Calendar targetCal) {
        Toast.makeText(context,
                "\n\n***\n"
                        + "Alarm has been set "
                        + targetCal.getTime()
                        + "\n" + "***\n",
                Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, datePicker.getDayOfMonth(), Toast.LENGTH_SHORT).show();

        intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), handler.numberOfAlarms() + 1, intent, 0);

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

        String.valueOf(targetCal.getTime());

        //Sending to database FUNKAR INTE behöver rätt tid och datum
        Alarm alarm = new Alarm();
        alarm.setDate(targetCal.get(targetCal.DAY_OF_MONTH), targetCal.get(targetCal.HOUR_OF_DAY), targetCal.get(targetCal.MINUTE));
        handler.insertAlarm(alarm);


    }
    public void stopAlarm(int id){
        Calendar targetCal = Calendar.getInstance();
        pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), id, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(alarmManager!=null) {
            //alarmManager.cancel(pendingIntent);
            Toast.makeText(context,"\n\n***\n"
                            + "The alarm set to "
                            + targetCal.getTime()
                            + "\n" + "has been cancelled." + "***\n",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
