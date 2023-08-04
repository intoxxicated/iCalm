package com.example.icalm.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.icalm.R;
import com.example.icalm.databinding.ActivityHomeBinding;
import com.example.icalm.fragment.Home.MusicFragment;
import com.example.icalm.fragment.Profile.EditProfileFragment;
import com.example.icalm.fragment.Home.HomeFragment;
import com.example.icalm.fragment.Journal.JournalFragment;
import com.example.icalm.fragment.Profile.ProfileFragment;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_profile) {
                    loadFragment(new ProfileFragment());
                    return true;
                }
                if (id == R.id.nav_home) {
                    loadFragment(new HomeFragment());
                    return true;
                }
                if (id == R.id.nav_journal) {
                    loadFragment(new JournalFragment());
                    return true;
                }
                if (id == R.id.nav_music) {
                    loadFragment(new MusicFragment());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            assert data != null;
            Uri uri = data.getData();
            Fragment editProfileFragment = getSupportFragmentManager().getFragments().get(0);
            if (editProfileFragment instanceof EditProfileFragment) {
                ((EditProfileFragment) editProfileFragment).uploadImage(uri);
            }
        } else {
            Log.e("else", "error");
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
