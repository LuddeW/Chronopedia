package com.example.ludwig.chronopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Models.Alarm;

public class LandingActivity extends AppCompatActivity {
    TextView landingTextView;
    Button landingButton;

    DatabaseHandler handler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        initComponents();
    }

    private void initComponents(){
        landingTextView = (TextView) findViewById(R.id.conditionTextView);
        landingButton = (Button) findViewById(R.id.landingButton);

        landingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String title = "test2";
                int day = 1;
                int hour = 01;
                int min = 06;

                Alarm a = new Alarm();
                a.setTitle(title);
                a.setDay(day);
                a.setHour(hour);
                a.setMin(min);

                handler.insertAlarm(a);
//                handler.deleteAll();

                Intent i = new Intent(LandingActivity.this, MyAlarmsActivity.class);
                startActivity(i);
            }
            });
    }
}
