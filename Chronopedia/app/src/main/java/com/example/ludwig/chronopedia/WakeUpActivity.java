package com.example.ludwig.chronopedia;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WakeUpActivity extends AppCompatActivity {
    Button btnStopAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);

    }

    private void initComponenents(){

        btnStopAlarm = (Button) findViewById(R.id.btnStopAlarm);
        btnStopAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "Alarm turned off.", Toast.LENGTH_SHORT).show();
                if(AlarmReceiver.ringtone != null){
                    AlarmReceiver.ringtone.stop();
                }
            }});
    }
}
