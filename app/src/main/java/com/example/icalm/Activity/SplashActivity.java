package com.example.icalm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.icalm.R;
import com.example.icalm.slider.SliderActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        long startTime = System.currentTimeMillis();

// app time
        long endTime = System.currentTimeMillis();
        long elapsedTimeMillis = endTime - startTime;
        int elapsedMinutes = (int) (elapsedTimeMillis / (1000 * 60)); // Convert milliseconds to minutes

        SharedPreferences sharedPreferences = getSharedPreferences("app_time", MODE_PRIVATE);
        int totalAppTime = sharedPreferences.getInt("total_app_time", 0);
        totalAppTime += elapsedMinutes;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("total_app_time", totalAppTime);
        editor.apply();

        ImageView imageView = findViewById(R.id.ivSplash);
        Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        imageView.startAnimation(zoomAnimation);

        // Delay and start your main activity after the animation finishes
        final Intent intent = new Intent(this, SliderActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
