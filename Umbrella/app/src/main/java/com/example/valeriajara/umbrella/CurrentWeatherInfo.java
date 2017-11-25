package com.example.valeriajara.umbrella;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class CurrentWeatherInfo extends Activity {
    TextView cityName;
    TextView rainFall;
    APICaller apiCaller;
    String cityNameText;
    String apiName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather_info);
            setContentView(R.layout.activity_current_weather_info);
            cityName= findViewById(R.id.cityName);
            rainFall = findViewById(R.id.rainFall);
            cityNameText = getIntent().getStringExtra("cityName");
            apiName = getIntent().getStringExtra("apiName");
            apiCaller = new APICaller(apiName);
            cityName.setText(cityNameText);

            ProgressDialog progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
            rainFall.setText(apiCaller.getWeather());
            progress.dismiss();
        }
    }
