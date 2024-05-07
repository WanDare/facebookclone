package com.example.mad_lab_testing.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_lab_testing.databinding.ActivityMainBinding;
import com.example.mad_lab_testing.model.User;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Add event listener
        binding.editProfile.setOnClickListener(v -> startEditProfileActivity());

        // Read data from intent
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        binding.txtFName.setText(firstName);
        binding.txtLName.setText(lastName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String firstName = data.getStringExtra("firstName");
            String lastName = data.getStringExtra("lastName");
            binding.txtFName.setText(firstName);
            binding.txtLName.setText(lastName);
        }
    }

    private void startEditProfileActivity() {
        Intent intent = new Intent(this, EditProfileActivity.class);
        String firstName = binding.txtFName.getText().toString();
        String lastName = binding.txtLName.getText().toString();
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        startActivityForResult(intent, 1);
    }

    private void startEditProfileActivityUsingObject() {
        Intent intent = new Intent(this, EditProfileActivity.class);
        User user = new User(1, "Wan", "Dara");
        intent.putExtra("user", user);
        startActivityForResult(intent, 1);
    }
}
