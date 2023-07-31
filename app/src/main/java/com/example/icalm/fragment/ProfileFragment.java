package com.example.icalm.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.example.icalm.HomeActivity;
import com.example.icalm.LoginActivity;
import com.example.icalm.R;
import com.example.icalm.databinding.FragmentHomeBinding;
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
                }    if(id==R.id.nav_edit_profile)
                {
                    loadFragment(new EditFragment());
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
    private void loadFragment(Fragment fragment) {

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Start a FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the existing fragment with the new fragment
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        // Add the transaction to the back stack (optional)
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }


}