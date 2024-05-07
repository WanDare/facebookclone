package com.example.mad_lab_testing.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_lab_testing.databinding.ActivityEditProfileBinding;
import com.example.mad_lab_testing.model.User;

public class EditProfileActivity extends AppCompatActivity {

    private ActivityEditProfileBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get data from intent
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        binding.editFName.setText(firstName);
        binding.editLName.setText(lastName);

        User user = (User) intent.getSerializableExtra("user");

        binding.btnSave.setOnClickListener( v -> onSaveClick() );
    }

    private void onSaveClick() {
        String firstName = binding.editFName.getText().toString();
        String lastName = binding.editLName.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        setResult(RESULT_OK, intent);
        finish();
    }
}
