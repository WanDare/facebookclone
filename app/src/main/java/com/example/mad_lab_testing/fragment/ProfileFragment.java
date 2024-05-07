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
import androidx.fragment.app.Fragment;

import com.example.mad_lab_testing.activity.EditProfileActivity;
import com.example.mad_lab_testing.databinding.FragmentProfileBinding;
import com.example.mad_lab_testing.model.User;

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
}
