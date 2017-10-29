package com.example.ludwig.chronopedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyAlarmsActivity extends AppCompatActivity {

    ListView listView;
    private int pos;
    private String lol = "lolol";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_alarms);
        initialize();
    }

    private void initialize() {
        ArrayAdapter<String> adapter = new AlarmAdapter(this,
                new String[]{lol});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                //När man klickar på ett av alarmen.
            }
        });
    }
}
