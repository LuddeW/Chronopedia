package com.example.ludwig.chronopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import Handlers.AlarmHandler;
import Models.Alarm;

public class MyAlarmsActivity extends AppCompatActivity {

    ListView listView;
    Button btnAddAlarm;
    Button btnBack;
    private int pos;
    AlarmHandler alarmHandler;

    DatabaseHandler handler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_alarms);
        listView = (ListView) findViewById(R.id.lvAlarms);
        btnAddAlarm = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button) findViewById(R.id.btnBack);
        alarmHandler = new AlarmHandler(getApplicationContext());
        initialize();
    }

    private void initialize() {
        String[] alarms = new String[(int)handler.getAlarmRows()];
        Alarm[] a = handler.getAllElements();
        for(int i = 0; i < alarms.length; i++)
        {
            alarms[i] = a[i].getDate();
        }
        ArrayAdapter<String> adapter = new AlarmAdapter(this,
                alarms);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                //När man klickar på ett av alarmen.
                if (handler.numberOfAlarms() == 1){
                    handler.deleteAll();
                }
                else{
                handler.deleteEntry(pos);
                alarmHandler.stopAlarm(pos);
                }
                finish();
                startActivity(getIntent());
            }
        });
        ///
        btnAddAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyAlarmsActivity.this, SetAlarmActivity.class);
                startActivity(i);
            }
        });
        ///
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyAlarmsActivity.this, LandingActivity.class);
                startActivity(i);
            }
        });
    }
}
