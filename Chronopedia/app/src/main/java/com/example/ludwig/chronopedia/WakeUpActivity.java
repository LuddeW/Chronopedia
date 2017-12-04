package com.example.ludwig.chronopedia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Data.Channel;
import Data.Item;
import Service.WeatherServiceCallback;
import Service.WikipediaDateEvents;
import Service.YahooWeatherService;

public class WakeUpActivity extends AppCompatActivity implements WeatherServiceCallback {
    Button btnStopAlarm;
    Button btnLandingActivity;
    TextView wikiTextView;
    public static Ringtone ringtone;
    TextView conditionTextView;
    TextView temperatureTextView;
    TextView locationTextView;
    ImageView weatherIconImageView;
    private YahooWeatherService service;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);
        initComponenents();
    }

    private void initComponenents(){

        conditionTextView = (TextView) findViewById(R.id.conditionTextView);
        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
        weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageView);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather("Malmo, Sweden");
        wikiTextView = (TextView) findViewById(R.id.wikiTextView);
        new WikipediaDateEvents(wikiTextView).execute();

        //Plays the ringtone for the alarm
        if(ringtone == null) {
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
            ringtone.play();
        }
        btnStopAlarm = (Button) findViewById(R.id.btnStopAlarm);
        btnLandingActivity = (Button) findViewById(R.id.btnLandingActivity);
        btnLandingActivity.setVisibility(View.INVISIBLE);

        //Turn off the alarm
        btnStopAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "Alarm turned off.", Toast.LENGTH_SHORT).show();
                if(ringtone != null){
                    ringtone.stop();
                }
                btnStopAlarm.setVisibility(View.INVISIBLE);
                btnLandingActivity.setVisibility(View.VISIBLE);

            }});
        //Return to main menu
        btnLandingActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent landingIntent = new Intent(getApplicationContext(), LandingActivity.class);
                getApplicationContext().startActivity(landingIntent);
            }});

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
