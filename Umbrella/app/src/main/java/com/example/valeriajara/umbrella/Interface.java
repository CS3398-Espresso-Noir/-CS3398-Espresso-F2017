package com.example.valeriajara.umbrella;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.google.firebase.messaging.FirebaseMessaging;

public class Interface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        ToggleButton austin = findViewById(R.id.subAustinBtn);
        ToggleButton houston = findViewById(R.id.subHoustonBtn);
        ToggleButton sanMarcos = findViewById(R.id.subSanMarcosBtn);
        ToggleButton sanAntonio = findViewById(R.id.subSanAnBtn);

        Button currentWeatherForSanMarcosBtn = findViewById(R.id.currentWeatherForSanMarcosBtn);
//        Button currentWeatherForAustinBtn = findViewById(R.id.currentWeatherForAustinBtn);
//        Button currentWeatherForSanAntonioBtn = findViewById(R.id.currentWeatherForSanAntonioBtn);


        austin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseMessaging.getInstance().subscribeToTopic("Austin");
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("Austin");
                }
            }
        });

        houston.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseMessaging.getInstance().subscribeToTopic("Houston");
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("Houston");
                }
            }
        });

        sanAntonio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseMessaging.getInstance().subscribeToTopic("San_Antonio");
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("San_Antonio");
                }
            }
        });

        sanMarcos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseMessaging.getInstance().subscribeToTopic("San_Marcos");
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("San_Marcos");
                }
            }
        });
        currentWeatherForSanMarcosBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Interface.this, CurrentWeatherInfo.class);
                intent.putExtra("cityName", "San Marcos");
                startActivity(intent);
            }
        }));
//        currentWeatherForAustinBtn.setOnClickListener((new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Interface.this, CurrentWeatherInfo.class);
//                intent.putExtra("cityName", "Austin");
//                startActivity(intent);
//            }
//        }));
//        currentWeatherForSanAntonioBtn.setOnClickListener((new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Interface.this, CurrentWeatherInfo.class);
//                intent.putExtra("cityName", "San Antonio");
//                startActivity(intent);
//            }
//        }));
    }
}
