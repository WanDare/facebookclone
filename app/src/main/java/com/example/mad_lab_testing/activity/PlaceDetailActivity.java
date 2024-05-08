package com.example.mad_lab_testing.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mad_lab_testing.databinding.ActivityPlaceDetailBinding;
import com.example.mad_lab_testing.fragment.PlaceAboutFragment;
import com.example.mad_lab_testing.fragment.PlacePhotosFragment;
import com.example.mad_lab_testing.fragment.PlaceReviewsFragment;

public class PlaceDetailActivity extends AppCompatActivity {

    private ActivityPlaceDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPlaceDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showAboutFragment();

        binding.imgBack.setOnClickListener( v -> {
            finish();
        });

        binding.btnAbout.setOnClickListener( v -> showAboutFragment());
        binding.btnPhoto.setOnClickListener( v -> showPhotosFragment());
        binding.btnReviews.setOnClickListener( v -> showReviewsFragment());

    }

    private void showAboutFragment() {
        binding.btnAbout.setSelected(true);
        binding.btnPhoto.setSelected(false);
        binding.btnReviews.setSelected(false);
        showFragment(new PlaceAboutFragment());
    }

    private void showPhotosFragment() {
        binding.btnAbout.setSelected(false);
        binding.btnPhoto.setSelected(true);
        binding.btnReviews.setSelected(false);
        showFragment(new PlacePhotosFragment());
    }

    private void showReviewsFragment() {
        binding.btnAbout.setSelected(false);
        binding.btnPhoto.setSelected(false);
        binding.btnReviews.setSelected(true);
        showFragment(new PlaceReviewsFragment());
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.lytFragment.getId(), fragment);
        fragmentTransaction.commit();
    }

}
