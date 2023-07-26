package com.example.icalm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.icalm.databinding.ActivitySliderBinding;

public class SliderActivity extends AppCompatActivity {

    SliderAdapter sliderAdapter;
    ActivitySliderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySliderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sliderAdapter = new SliderAdapter(this);
        binding.vpSlider.setAdapter(sliderAdapter);
        binding.ivSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SliderActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        binding.btnNext.setOnClickListener(view -> showNextSlide());
    }


    public void showNextSlide() {
        int currentSlide = binding.vpSlider.getCurrentItem();
        if (currentSlide < sliderAdapter.getItemCount() - 1) {
            binding.vpSlider.setCurrentItem(currentSlide + 1);
        }
        else {
            Intent intent=new Intent(SliderActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
