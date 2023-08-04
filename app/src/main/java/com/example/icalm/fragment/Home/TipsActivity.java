package com.example.icalm.fragment.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;

import com.example.icalm.databinding.ActivityTipsBinding;

public class TipsActivity extends AppCompatActivity {
    ActivityTipsBinding binding;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTipsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WebSettings webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        binding.webView.loadUrl("https://www.calm.com/blog/what-is-mindfulness-meditation");
    }
}