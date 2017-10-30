package com.example.ludwig.chronopedia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Models.Alarm;
import Service.YahooWeatherService;

public class LandingActivity extends AppCompatActivity {
    TextView landingTextView;
    Button landingButton;

    DatabaseHandler handler = new DatabaseHandler(this);
import Data.Channel;
import Data.Item;
import Service.WeatherServiceCallback;
import Service.YahooWeatherService;

public class LandingActivity extends AppCompatActivity implements WeatherServiceCallback {
    TextView conditionTextView;
    TextView temperatureTextView;
    TextView locationTextView;
    ImageView weatherIconImageView;
    Button landingButton;

    private YahooWeatherService service;
    private ProgressDialog dialog;

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
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather("Malmo, Sweden");

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
        dialog.hide();

        Item item = channel.getItem();

        int resourceId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId, null);
        weatherIconImageView.setImageDrawable(weatherIconDrawable);
        temperatureTextView.setText(item.getCondition().getTemperature()+ "\u00B0"+ channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
