package com.example.valeriajara.umbrella;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                    FirebaseMessaging.getInstance().subscribeToTopic("San Antonio");
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("San Antonio");
                }
            }
        });

        sanMarcos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseMessaging.getInstance().subscribeToTopic("San Marcos");
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("San Marcos");
                }
            }
        });

    }
}
