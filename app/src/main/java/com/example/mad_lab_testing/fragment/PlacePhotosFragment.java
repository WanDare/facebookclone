package com.example.mad_lab_testing.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_lab_testing.adapter.PhotoAdapter;
import com.example.mad_lab_testing.api.ApiService;
import com.example.mad_lab_testing.databinding.FragmentPlacePhotosBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlacePhotosFragment extends Fragment {

    private FragmentPlacePhotosBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPlacePhotosBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadPhotos();
    }

    private void loadPhotos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/iteapp-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.loadPlacePhotos().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    showPhotos(response.body());
                } else {
                    Toast.makeText(requireContext(), "Error while loading data from server.", Toast.LENGTH_LONG).show();
                    Log.e("ite-app", "Error loading data: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable throwable) {
                Toast.makeText(requireContext(), "Error while loading data from server.", Toast.LENGTH_LONG).show();
                Log.e("ite-app", "Error loading data: " + throwable.getMessage());
            }
        });
    }

    private void showPhotos(List<String> photos) {

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(), 3);
        binding.recyclerView.setLayoutManager(layoutManager);

        PhotoAdapter adapter = new PhotoAdapter();
        adapter.setDataSet(photos);
        binding.recyclerView.setAdapter(adapter);

    }
}
