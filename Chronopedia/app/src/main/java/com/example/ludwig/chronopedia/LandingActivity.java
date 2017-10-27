package com.example.ludwig.chronopedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import Managers.ApiManager;

public class LandingActivity extends AppCompatActivity {
    TextView landingTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        initComponents();
    }

    private void initComponents(){
        landingTextView = (TextView) findViewById(R.id.landingTextView);
        ApiManager apiManager = new ApiManager(this);
        landingTextView.setText(apiManager.returnWeather());
    }
}
