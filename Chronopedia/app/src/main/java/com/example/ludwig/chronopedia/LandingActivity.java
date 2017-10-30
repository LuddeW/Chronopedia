package com.example.ludwig.chronopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Data.Channel;
import Service.WeatherServiceCallback;
import Service.YahooWeatherService;

public class LandingActivity extends AppCompatActivity implements WeatherServiceCallback {
    TextView conditionTextView;
    TextView temperatureTextView;
    TextView locationTextView;
    ImageView weatherIconImageView;
    Button landingButton;

    private YahooWeatherService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        initComponents();
    }

    private void initComponents(){
        conditionTextView = (TextView) findViewById(R.id.conditionTextView);
        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
        weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageView);
        landingButton = (Button) findViewById(R.id.landingButton);

        service = new YahooWeatherService(this);
        service.refreshWeather("Austin, TX");

        landingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(LandingActivity.this, MyAlarmsActivity.class);
                startActivity(i);
            }
            });
    }

    @Override
    public void serviceSuccess(Channel channel) {

    }

    @Override
    public void serviceFailure(Exception exception) {
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
