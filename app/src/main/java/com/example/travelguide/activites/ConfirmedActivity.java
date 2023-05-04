package com.example.travelguide.activites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelguide.databinding.ActivityConfirmedBinding;

public class ConfirmedActivity extends AppCompatActivity {
    ActivityConfirmedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.finishBTN.setOnClickListener(view -> {
            startActivity(new Intent(this, HomeScreenAdminActivity.class));
            finish();
        });


    }
}