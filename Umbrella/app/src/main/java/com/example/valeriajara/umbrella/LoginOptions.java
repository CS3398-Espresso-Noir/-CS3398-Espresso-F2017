package com.example.valeriajara.umbrella;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginOptions extends AppCompatActivity {
    Button btnSignInGoogle;
    Button btnSignInEmail;
    Button getBtnSignInFacebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options);
        btnSignInEmail = (Button) findViewById(R.id.btnSignInEmail);
        btnSignInGoogle = (Button) findViewById(R.id.btnSignInGoogle);
        getBtnSignInFacebook = (Button) findViewById(R.id.btnSignInFacebook);

    }
    protected  void signInEmail(View v) {
        Intent intent = new Intent(LoginOptions.this, MainActivity.class);
        Toast.makeText(LoginOptions.this, "UNDER CONSTRUCTION", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
    protected void signInGoogle(View v) {
        Intent intent = new Intent(LoginOptions.this, GoogleSignIn.class);
        startActivity(intent);
    }
    protected void signInFacebook(View v) {
        Intent intent = new Intent(LoginOptions.this, MainActivity.class);
        Toast.makeText(LoginOptions.this, "UNDER CONSTRUCTION", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}
