package com.example.mad_lab_testing.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mad_lab_testing.databinding.FragmentPlacePhotosBinding;

public class PlacePhotosFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentPlacePhotosBinding binding = FragmentPlacePhotosBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
}
