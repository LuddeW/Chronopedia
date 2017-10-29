package com.example.ludwig.chronopedia;

/**
 * Created by Martin on 2017-10-29.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        // display a message for the task
        Toast.makeText(context, "Running", Toast.LENGTH_SHORT).show();
    }
}
