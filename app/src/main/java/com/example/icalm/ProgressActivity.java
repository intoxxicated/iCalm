package com.example.icalm;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ProgressActivity extends AppCompatActivity {

    private TextView totalMinutesTextView;
    private TextView streakTextView;
    private Button meditateButton;
    private SharedPreferences sharedPreferences;
    private int totalMinutes = 0;
    private int streak = 0;
    private String lastMeditationDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        totalMinutesTextView = findViewById(R.id.totalMinutesTextView);
        streakTextView = findViewById(R.id.streakTextView);
        meditateButton = findViewById(R.id.meditateButton);

        sharedPreferences = getSharedPreferences("progress", 0);

        updateProgress();
        setupMeditateButton();
    }

    private void updateProgress() {
        totalMinutes = sharedPreferences.getInt("total_minutes", 0);
        streak = sharedPreferences.getInt("streak", 0);
        lastMeditationDate = sharedPreferences.getString("last_meditation_date", "");

        totalMinutesTextView.setText("Total Meditation Minutes: " + totalMinutes);
        streakTextView.setText("Current Streak: " + streak + " days");
    }

    private void setupMeditateButton() {
        meditateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meditate();
            }
        });
    }

    private void meditate() {
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String today = dateFormat.format(currentDate);

        if (!today.equals(lastMeditationDate)) {
            // New day, update streak
            streak++;
            sharedPreferences.edit()
                    .putInt("streak", streak)
                    .putString("last_meditation_date", today)
                    .apply();
        }

        SharedPreferences sharedPreferences = getSharedPreferences("app_time", MODE_PRIVATE);
        int totalAppTime = sharedPreferences.getInt("total_app_time", 0);
        totalMinutes += totalAppTime;
        sharedPreferences.edit()
                .putInt("total_minutes", totalMinutes)
                .apply();

        updateProgress();
    }
}
