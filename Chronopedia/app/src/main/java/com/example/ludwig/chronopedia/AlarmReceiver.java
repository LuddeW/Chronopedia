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
    @Override
    public void onReceive(Context context, Intent intent) {

        //landingActivity.cancelFragment.setVisibility(VISIBLE);
        Intent landingIntent = new Intent(context, LandingActivity.class);
        context.startActivity(landingIntent);
        //if(landingActivity.stopAlarmFragment != null)
        {
            //landingActivity.stopAlarmFragment.btnStopAlarm.setVisibility(View.VISIBLE);
            landingActivity.cancelFragment.setVisibility(VISIBLE);
        }
        Toast.makeText(context, "Good Morning", Toast.LENGTH_LONG).show();
        //landingActivity.cancelFragment.setVisibility(VISIBLE);
        //stopFragment.getView().setVisibility(View.VISIBLE);

    }
}
