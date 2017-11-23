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

    StopAlarmFragment stopFragment;
    LandingActivity landingActivity;
    public static Ringtone ringtone;

    @Override
    public void onReceive(Context context, Intent intent) {

        //landingActivity.cancelFragment.setVisibility(VISIBLE);
        Intent landingIntent = new Intent(context, LandingActivity.class);
        context.startActivity(landingIntent);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(context, uri);
        ringtone.play();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                ringtone.stop();
            }
        }, 1000 * 60);
        Toast.makeText(context, "Good Morning", Toast.LENGTH_LONG).show();
        //landingActivity.cancelFragment.setVisibility(VISIBLE);
        //stopFragment.getView().setVisibility(View.VISIBLE);

    }
}
