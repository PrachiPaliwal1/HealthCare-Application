package com.threehero.myhealth.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.threehero.myhealth.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        long delayMillis;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent sp = new Intent(Splash.this, LoginPage.class);
                startActivity(sp);
                finish();

            }

        }, delayMillis = 3000);

    }
}
