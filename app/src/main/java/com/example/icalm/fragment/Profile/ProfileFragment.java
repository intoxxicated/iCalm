package com.example.icalm.fragment.Profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.example.icalm.Activity.LoginActivity;
import com.example.icalm.R;
import com.example.icalm.databinding.FragmentProfileBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment
{
    FragmentProfileBinding binding;
    AlertDialog dialog;



    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id =item.getItemId();
                if(id==R.id.nav_Logout)
                {
                    showLogoutAlert();
                }
                if(id==R.id.nav_edit_profile)
                {
                    loadFragment(new EditProfileFragment());
                }
                if(id==R.id.nav_look_trainer)
                {
                    searchYogaCenterOnMap();
                }
                if(id==R.id.nav_contact_us)
                {
                   loadFragment(new ContactUsFragment());
                }
                return true;
            }
        });

        return binding.getRoot();


    }
    private  void showLogoutAlert()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to Logout ?  ")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "LOGGING OUT", Toast.LENGTH_SHORT).show();
                        clearLoginState();
                        performLogout();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
        dialog=builder.create();
        dialog.show();


    }

        private void performLogout() {
        // Clear the login state from SharedPreferences
        clearLoginState();

        // Perform the Firebase logout
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

    private void clearLoginState() {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
    }


    private void searchYogaCenterOnMap() {
        String location = "geo:0,0?q=Yoga+Trainer";
        Uri gmmIntentUri = Uri.parse(location);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        Log.e("map", ""+mapIntent.getData());
        startActivity(mapIntent);
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}