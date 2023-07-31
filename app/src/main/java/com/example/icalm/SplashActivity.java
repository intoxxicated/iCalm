package com.example.icalm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.icalm.slider.SliderActivity;

public class SplashActivity extends AppCompatActivity {

        private static final int SPLASH_DELAY = 2000;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);

            new Handler().postDelayed(() -> {

                Intent intent = new Intent(SplashActivity.this, SliderActivity.class);
                startActivity(intent);
                finish();
            }, SPLASH_DELAY);


        }
    }

