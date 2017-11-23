package com.example.ludwig.chronopedia;


import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class StopAlarmFragment extends Fragment
{
    Button btnStopAlarm;

    public StopAlarmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stop_alarm, container, false);

        btnStopAlarm = (Button) view.findViewById(R.id.btnStopAlarm);
        btnStopAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getContext(), "Alarm turned off.", Toast.LENGTH_SHORT).show();
                getView().setVisibility(View.INVISIBLE);
                if(AlarmReceiver.ringtone != null){
                    AlarmReceiver.ringtone.stop();
                    //AlarmReceiver.ringtone = null;
                }
                //This is called when pressing the round button in landing activity
            }});
        return view;
    }
}
