package com.example.travelguide.activites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.travelguide.adapters.BookingRequestAdapter;
import com.example.travelguide.databinding.ActivityBookingRequestBinding;
import com.example.travelguide.models.BookingModel;
import com.example.travelguide.retrofit.APICONTROLLER;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingRequestActivity extends AppCompatActivity {
    ActivityBookingRequestBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingRequestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerview.setHasFixedSize(false);


        showBookingList();
        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });


    }

    private void showBookingList() {

        Call<List<BookingModel>> booking = APICONTROLLER.getInstance().getAPI().reqst();
        booking.enqueue(new Callback<List<BookingModel>>() {
            @Override
            public void onResponse(Call<List<BookingModel>> call, Response<List<BookingModel>> response) {
                BookingRequestAdapter adapter = new BookingRequestAdapter(response.body(), BookingRequestActivity.this, new BookingRequestAdapter.onClick() {
                    @Override
                    public void onClick(BookingModel model) {
                        Intent intent = new Intent(BookingRequestActivity.this, BookingConfirmationActivity.class);
                        intent.putExtra("model", model);
                        startActivity(intent);
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