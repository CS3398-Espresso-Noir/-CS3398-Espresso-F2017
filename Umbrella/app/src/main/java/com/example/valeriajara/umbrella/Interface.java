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

        Button austin = findViewById(R.id.subAustinBtn);
        Button houston = findViewById(R.id.subHoustonBtn);
        Button sanMarcos = findViewById(R.id.subSanMarcosBtn);
        Button sanAntonio = findViewById(R.id.subSanAnBtn);

        sanMarcos.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Interface.this, CurrentWeatherInfo.class);
                intent.putExtra("cityName", "San Marcos");
                intent.putExtra("apiName", "San_Marcos");
                intent.putExtra("imageName", "san_marcos");
                startActivity(intent);
            }
        }));
        austin.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Interface.this, CurrentWeatherInfo.class);
                intent.putExtra("cityName", "Austin");
                intent.putExtra("apiName", "Austin");
                intent.putExtra("imageName", "austin");
                startActivity(intent);
            }
        }));
        houston.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Interface.this, CurrentWeatherInfo.class);
                intent.putExtra("cityName", "Houston");
                intent.putExtra("apiName", "Houston");
                intent.putExtra("imageName", "houston");
                startActivity(intent);
            }
        }));
        sanAntonio.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Interface.this, CurrentWeatherInfo.class);
                intent.putExtra("cityName", "San Antonio");
                intent.putExtra("apiName", "San_Antonio");
                intent.putExtra("imageName", "san_antonio");
                startActivity(intent);
            }
        }));
    }
}
