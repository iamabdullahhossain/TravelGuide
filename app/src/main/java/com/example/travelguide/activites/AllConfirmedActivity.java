package com.example.travelguide.activites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.travelguide.adapters.AllConfirmedBookingAdapter;
import com.example.travelguide.adapters.BookingRequestAdapter;
import com.example.travelguide.databinding.ActivityAllConfirmedBinding;
import com.example.travelguide.models.BookingModel;
import com.example.travelguide.retrofit.APICONTROLLER;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllConfirmedActivity extends AppCompatActivity {
    ActivityAllConfirmedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllConfirmedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerview.setHasFixedSize(false);

        showAllConfirmed();

    }

    private void showAllConfirmed() {

        Call<List<BookingModel>> booking = APICONTROLLER.getInstance().getAPI().allConfirmed();
        booking.enqueue(new Callback<List<BookingModel>>() {
            @Override
            public void onResponse(Call<List<BookingModel>> call, Response<List<BookingModel>> response) {
                AllConfirmedBookingAdapter adapter = new AllConfirmedBookingAdapter(response.body(), AllConfirmedActivity.this, new BookingRequestAdapter.onClick() {
                    @Override
                    public void onClick(BookingModel model) {

                    }
                });
                binding.recyclerview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<BookingModel>> call, Throwable t) {

            }
        });
    }
}