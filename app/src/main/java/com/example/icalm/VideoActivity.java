package com.example.icalm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;

import com.example.icalm.databinding.ActivityTipsBinding;
import com.example.icalm.databinding.ActivityVideoBinding;

public class VideoActivity extends AppCompatActivity {

    ActivityVideoBinding binding;
    String url;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WebSettings webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Intent intent=getIntent();
        url=intent.getStringExtra("url");

        binding.webView.loadUrl(url);
    }
}