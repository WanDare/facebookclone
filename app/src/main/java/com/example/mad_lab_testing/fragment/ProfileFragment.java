package com.example.mad_lab_testing.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.mad_lab_testing.activity.EditProfileActivity;
import com.example.mad_lab_testing.api.ApiService;
import com.example.mad_lab_testing.databinding.FragmentProfileBinding;
import com.example.mad_lab_testing.model.Profile;
import com.example.mad_lab_testing.model.User;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    private ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    handleOnEditProfileSaved(result);
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.editProfile.setOnClickListener( v -> startEditProfileActivity());

        loadProfileData();
    }

    private void startEditProfileActivity() {
        Intent intent = new Intent(requireContext(), EditProfileActivity.class);
        User user = new User(1,"Dara", "Wan");
        intent.putExtra("user", user);
        activityLauncher.launch(intent);
    }

    private void handleOnEditProfileSaved(ActivityResult result) {
        if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            String firstName = result.getData().getStringExtra("firstName");
            String lastName = result.getData().getStringExtra("lastName");
            binding.txtFName.setText(firstName);
            binding.txtLName.setText(lastName);
        }
    }

    private void loadProfileData() {
        showLoading();
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("https://10.0.2.2/iteapp-api/")
                .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/iteapp-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.loadProfile().enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                hideLoading();
                if(response.isSuccessful()) {
                    displayProfile(response.body());
                } else {
                    showErrorDialog(response.message());
                    showErrorContent();
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable throwable) {
                hideLoading();
                showErrorDialog(throwable.getMessage());
                showErrorContent();
            }
        });
    }

    private void displayProfile(Profile profile) {
        binding.txtFName.setText(profile.getFullName());
        Picasso.get()
                .load(profile.getCoverImage())
                .into(binding.profileBackground);
        Picasso.get()
                .load(profile.getProfileImage())
                .into(binding.profileAvatar);
    }

    private void showErrorDialog(String message) {
        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .create();
        dialog.show();
    }

    private void showLoading() {
        binding.lytContent.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
        binding.lytContent.setVisibility(View.VISIBLE);
    }

    private void showErrorContent() {
        binding.lytContent.setVisibility(View.GONE);
        binding.lytError.setVisibility(View.VISIBLE);
    }

}
