package com.example.ludwig.chronopedia;


import android.app.Application;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Data.Channel;
import Data.Item;
import Service.WeatherServiceCallback;
import Service.WikipediaDateEvents;
import Service.YahooWeatherService;

public class StopAlarmFragment extends Fragment implements WeatherServiceCallback
{
    TextView conditionTextView;
    TextView temperatureTextView;
    TextView locationTextView;
    TextView wikiTextView;
    ImageView weatherIconImageView;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    public StopAlarmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stop_alarm, container, false);
        initComponents(view);
//
//        btnStopAlarm = (Button) view.findViewById(R.id.btnStopAlarm);
//        btnStopAlarm.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(getContext(), "Alarm turned off.", Toast.LENGTH_SHORT).show();
//                getView().setVisibility(View.INVISIBLE);
//                if(AlarmReceiver.ringtone != null){
//                    AlarmReceiver.ringtone.stop();
//                    //AlarmReceiver.ringtone = null;
//                }
//                //This is called when pressing the round button in landing activity
//            }});

        return view;
    }

    private void initComponents(View view)
    {
        conditionTextView = (TextView) view.findViewById(R.id.conditionTextView);
        temperatureTextView = (TextView) view.findViewById(R.id.temperatureTextView);
        locationTextView = (TextView) view.findViewById(R.id.locationTextView);
        wikiTextView = (TextView) view.findViewById(R.id.wikiTextView);
        weatherIconImageView = (ImageView) view.findViewById(R.id.weatherIconImageView);
        service = new YahooWeatherService();
        //dialog = new ProgressDialog(getContext());
        //dialog.setMessage("Loading...");
        //dialog.show();
        service.refreshWeather("Malmo, Sweden");
//        new WikipediaDateEvents(wikiTextView).execute();
    }
    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();

        int resourceId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getActivity().getPackageName());
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId, null);
        weatherIconImageView.setImageDrawable(weatherIconDrawable);
        temperatureTextView.setText(item.getCondition().getTemperature()+ "\u00B0"+ channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
