package com.example.mad_lab_testing.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mad_lab_testing.databinding.FragmentPlaceReviewsBinding;

public class PlaceReviewsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentPlaceReviewsBinding binding = FragmentPlaceReviewsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
}
