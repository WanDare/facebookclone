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

import com.example.mad_lab_testing.adapter.ProvinceAdapter;
import com.example.mad_lab_testing.api.ApiService;
import com.example.mad_lab_testing.databinding.FragmentProvincesBinding;
import com.example.mad_lab_testing.model.Province;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProvincesFragment extends Fragment {

    private FragmentProvincesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentProvincesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadProvinceList();
    }

    private void loadProvinceList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/iteapp-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.loadProvinceList().enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {
                if(response.isSuccessful()) {
                    showProvinceList(response.body());
                } else {
                    Toast.makeText(requireContext(), "Error while loading data from server.", Toast.LENGTH_LONG).show();
                    Log.e("ite-app", "Error loading data: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Province>> call, Throwable throwable) {
                Toast.makeText(requireContext(), "Error while loading data from server.", Toast.LENGTH_LONG).show();
                Log.e("ite-app", "Error loading data: " + throwable.getMessage());
            }
        });
    }

    private void showProvinceList(List<Province> provinces) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.recyclerView.setLayoutManager(layoutManager);

        ProvinceAdapter adapter = new ProvinceAdapter();
        adapter.setProvinces(provinces);
        binding.recyclerView.setAdapter(adapter);

    }

}
