package com.example.travelguide.activites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelguide.databinding.ActivityHomeScreenAdminBinding;

public class HomeScreenAdminActivity extends AppCompatActivity {
    ActivityHomeScreenAdminBinding binding;
    String Code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeScreenAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        Code = intent.getStringExtra("code");

        binding.createPackageClick.setOnClickListener(view -> {

            Intent intent1 = new Intent(HomeScreenAdminActivity.this, CreatePackageActivity.class);
            intent1.putExtra("code", Code);
            startActivity(intent1);

        });

        binding.AllPackageClick.setOnClickListener(view -> {
            startActivity(new Intent(HomeScreenAdminActivity.this, AllCreatedPackageActivity.class));
        });

        binding.bookingRequestClick.setOnClickListener(view -> {

            Intent intent1 = new Intent(HomeScreenAdminActivity.this, BookingRequestActivity.class);
            startActivity(intent1);


        });

        binding.ConfirmedBookingsClick.setOnClickListener(view -> {
            Intent intent1 = new Intent(HomeScreenAdminActivity.this, AllConfirmedActivity.class);
            startActivity(intent1);
        });


    }
}