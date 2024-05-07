package com.example.mad_lab_testing.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mad_lab_testing.R;
import com.example.mad_lab_testing.databinding.ActivityLandingBinding;
import com.example.mad_lab_testing.fragment.AccountFragment;
import com.example.mad_lab_testing.fragment.HomeFragment;
import com.example.mad_lab_testing.fragment.MoreFragment;
import com.example.mad_lab_testing.fragment.ProfileFragment;
import com.example.mad_lab_testing.fragment.ProvincesFragment;
import com.example.mad_lab_testing.fragment.SearchFragment;
import com.google.android.material.navigation.NavigationBarView;

public class LandingActivity extends AppCompatActivity {

    private ActivityLandingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Create Home fragment object
        HomeFragment homeFragment = new HomeFragment();
        showFragment(homeFragment);

        // Handle bottom navigation view item click
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return handleOnNavigationItemSelected(item);
            }
        });
    }

    private boolean handleOnNavigationItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.mnuHome) {
            showFragment(new HomeFragment());
        } else if (item.getItemId() == R.id.mnuProvince) {
            showFragment(new ProvincesFragment());
        } else if (item.getItemId() == R.id.mnuSearch) {
            showFragment(new SearchFragment());
        } else if (item.getItemId() == R.id.mnuAccount) {
            showFragment(new ProfileFragment());
        } else {
            showFragment(new MoreFragment());
        }

        return true;
    }

    private void showFragment(Fragment fragment) {
        // Fragment Manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Fragment transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace fragment
        fragmentTransaction.replace(binding.lytFragment.getId(), fragment);

        // Commit Transaction
        fragmentTransaction.commit();
    }
}
