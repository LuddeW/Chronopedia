package com.example.ludwig.chronopedia;

/**
 * Created by Martin on 2017-10-29.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

public class AlarmReceiver extends BroadcastReceiver{
   public static Ringtone ringtone;
    @Override
    public void onReceive(Context context, Intent intent) {

        // display a message for the task
        Toast.makeText(context, "Good Morning", Toast.LENGTH_SHORT).show();



//        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//        ringtone = RingtoneManager.getRingtone(context, uri);
//        ringtone.play();
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ringtone.stop();
//            }
//        }, 1000 * 60);

    }
}
