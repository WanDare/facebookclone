package com.example.mad_lab_testing.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_lab_testing.databinding.ActivityMainBinding;

public class Profile2Activity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String firstName = result.getData().getStringExtra("firstName");
                        String lastName = result.getData().getStringExtra("lastName");
                        binding.txtFName.setText(firstName);
                        binding.txtLName.setText(lastName);
                    }
                }
            }
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        // Add event listener
        binding.editProfile.setOnClickListener( v -> startEditProfileActivity() );

        // Read data from intent
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        binding.txtFName.setText(firstName);
        binding.txtLName.setText(lastName);
    }

    private void startEditProfileActivity() {
        Intent intent = new Intent(this, EditProfileActivity.class);
        String firstName = binding.txtFName.getText().toString();
        String lastName = binding.txtLName.getText().toString();
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        activityLauncher.launch(intent);
    }
}
