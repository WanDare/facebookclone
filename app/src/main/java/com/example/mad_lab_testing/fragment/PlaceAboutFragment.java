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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_lab_testing.adapter.PlaceAdapter;
import com.example.mad_lab_testing.api.ApiService;
import com.example.mad_lab_testing.databinding.FragmentPlaceAboutBinding;
import com.example.mad_lab_testing.model.PlaceAbout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlaceAboutFragment extends Fragment {

    private FragmentPlaceAboutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentPlaceAboutBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadPlaceAboutInfo();
    }

    private void loadPlaceAboutInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/iteapp-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.loadPlaceAbout().enqueue(new Callback<PlaceAbout>() {
            @Override
            public void onResponse(Call<PlaceAbout> call, Response<PlaceAbout> response) {
                if (response.isSuccessful()) {
                    showPlaceAbout(response.body());
                } else {
                    Toast.makeText(requireContext(), "Error while loading data from server.", Toast.LENGTH_LONG).show();
                    Log.e("ite-app", "Error loading data: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PlaceAbout> call, Throwable throwable) {
                Toast.makeText(requireContext(), "Error while loading data from server.", Toast.LENGTH_LONG).show();
                Log.e("ite-app", "Error loading data: " + throwable.getMessage());
            }
        });
    }

    private void showPlaceAbout(PlaceAbout placeAbout) {
        binding.txtName.setText(placeAbout.getName());
        binding.txtDesc.setText(placeAbout.getDescription());
        binding.txtAddress.setText(placeAbout.getAddress());

        // show relate places
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        PlaceAdapter adapter = new PlaceAdapter();
        adapter.setDataSet(placeAbout.getRelatedPlaces());
        binding.recyclerView.setAdapter(adapter);

    }

}
