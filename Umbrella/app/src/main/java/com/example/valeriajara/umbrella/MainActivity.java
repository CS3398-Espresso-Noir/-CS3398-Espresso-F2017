package com.example.valeriajara.umbrella;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mainSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainSignIn = (Button) findViewById(R.id.btnSignIn);
    }
    protected void signIn(View v) {
        Intent intent = new Intent(MainActivity.this, LoginOptions.class);
        startActivity(intent);
    }

}
