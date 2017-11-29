package com.example.ludwig.chronopedia;

/**
 * Created by Martin on 2017-10-29.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import static android.view.View.VISIBLE;

public class AlarmReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {

        Intent wakeUpIntent = new Intent(context, WakeUpActivity.class);
        context.startActivity(wakeUpIntent);
        Toast.makeText(context, "Good Morning", Toast.LENGTH_LONG).show();
    }
}
