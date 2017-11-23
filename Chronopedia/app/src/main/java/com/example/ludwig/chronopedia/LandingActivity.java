package com.example.ludwig.chronopedia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import Data.Channel;
import Data.Item;
import Models.Alarm;
import Service.WeatherServiceCallback;
import Service.WikipediaDateEvents;
import Service.YahooWeatherService;

public class LandingActivity extends AppCompatActivity implements WeatherServiceCallback {
    TextView conditionTextView;
    TextView temperatureTextView;
    TextView locationTextView;
    TextView wikiTextView;
    ImageView weatherIconImageView;
    Button landingButton;
    View cancelFragment;
    DatabaseHandler handler = new DatabaseHandler(this);

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
        wikiTextView = (TextView) findViewById(R.id.wikiTextView);
        weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageView);
        landingButton = (Button) findViewById(R.id.landingButton);
        cancelFragment = (View) findViewById(R.id.fragmentStopAlarm);

        //cancelFragment.setVisibility(View.GONE);


        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather("Malmo, Sweden");
        new WikipediaDateEvents(wikiTextView).execute();

        landingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
//                int day = 1;
//                int hour = 11;
//                int min = 56;
//
//                Alarm a = new Alarm();
//                a.setDate(day, hour, min);
//                handler.insertAlarm(a);
//                handler.deleteAll();
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
