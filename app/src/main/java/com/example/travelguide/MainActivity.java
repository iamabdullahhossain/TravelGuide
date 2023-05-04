package com.example.travelguide;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelguide.activites.AdminLoginActivity;
import com.example.travelguide.activites.TouristActivity;
import com.example.travelguide.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.adminBTN.setOnClickListener(view -> {
            startActivity(new Intent(this, AdminLoginActivity.class));


        });

        binding.touristBTN.setOnClickListener(view -> {
            startActivity(new Intent(this, TouristActivity.class));
        });

    }
}