package com.example.travelguide.activites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.travelguide.adapters.RunningPackageAdapter;
import com.example.travelguide.databinding.ActivityAllActivatedPackageBinding;
import com.example.travelguide.models.RunningPackageModel;
import com.example.travelguide.retrofit.APICONTROLLER;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllActivatedPackageActivity extends AppCompatActivity {

    ActivityAllActivatedPackageBinding binding;
    String ContactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllActivatedPackageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        ContactNumber = intent.getStringExtra("contact");

        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerview.setHasFixedSize(false);

        Call<List<RunningPackageModel>> running = APICONTROLLER.getInstance().getAPI().running(ContactNumber);
        running.enqueue(new Callback<List<RunningPackageModel>>() {
            @Override
            public void onResponse(Call<List<RunningPackageModel>> call, Response<List<RunningPackageModel>> response) {
                RunningPackageAdapter adapter = new RunningPackageAdapter(response.body(), AllActivatedPackageActivity.this);
                binding.recyclerview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<RunningPackageModel>> call, Throwable t) {

            }
        });


    }
}