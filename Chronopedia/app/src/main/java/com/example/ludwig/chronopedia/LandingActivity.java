package com.example.ludwig.chronopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Managers.ApiManager;

public class LandingActivity extends AppCompatActivity {
    TextView landingTextView;
    Button landingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        initComponents();
    }

    private void initComponents(){
        landingTextView = (TextView) findViewById(R.id.landingTextView);
        landingButton = (Button) findViewById(R.id.landingButton);

        ApiManager apiManager = new ApiManager(this);
        landingTextView.setText(apiManager.returnWeather());

        landingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(LandingActivity.this, MyAlarmsActivity.class);
                startActivity(i);
            }
            });
    }
}
