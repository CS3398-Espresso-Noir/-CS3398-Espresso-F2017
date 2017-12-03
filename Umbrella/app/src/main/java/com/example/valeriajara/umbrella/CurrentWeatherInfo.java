package com.example.valeriajara.umbrella;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CurrentWeatherInfo extends Activity {
    TextView cityName;
    TextView rainFall;
    TextView temperature;
    ImageView icon;
    int iconNumber;
    APICaller apiCaller;
    String cityNameText;
    String apiName;
    String imageName;
    String iconName;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather_info);
        setContentView(R.layout.activity_current_weather_info);
        cityName= findViewById(R.id.cityName);
        rainFall = findViewById(R.id.rainFall);

        cityNameText = getIntent().getStringExtra("cityName");
        apiName = getIntent().getStringExtra("apiName");
        imageName = getIntent().getStringExtra("imageName");
        int imageId = getResources().getIdentifier(imageName, "drawable", "com.example.valeriajara.umbrella");
        constraintLayout = findViewById(R.id.layoutId);
        icon = findViewById(R.id.imageViewIcon);
        constraintLayout.setBackgroundResource(imageId);
        //temperature = findViewById(R.id.temperature);
        apiCaller = new APICaller(apiName);
        APICaller.callAPI(); // CALLS API
        iconNumber = apiCaller.getCondition();
        Log.e("condition", Integer.toString(iconNumber));
        Log.e("iconName","i"+Integer.toString(iconNumber));
        iconName = "i"+Integer.toString(iconNumber);

        int iconId = getResources().getIdentifier(iconName,"drawable","com.example.valeriajara.umbrella");
        icon.setImageResource(iconId);
        cityName.setText(cityNameText);

        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        rainFall.setText(apiCaller.getRainfall());
      //  temperature.setText(apiCaller.getTemperatureF());
      //  iconNumber = apiCaller.getCondition();

        progress.dismiss();
    }
//    String getConditionString(int number) {
//        String condition = new String();
//        switch(number) {
//            case 1: condition = ""
//            case 2:
//            case 3:
//            case 4:
//            case 5:
//            case 6:
//            case 7:
//            case 8:
//            case 9:
//            case 10:
//            case 11:
//            case 12:
//            case 13:
//            case 14:
//            case 15:
//            default:
//        }
//    }
}
