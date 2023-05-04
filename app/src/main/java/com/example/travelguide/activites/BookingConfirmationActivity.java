package com.example.travelguide.activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.travelguide.R;
import com.example.travelguide.databinding.ActivityBookingConfirmationBinding;
import com.example.travelguide.models.BookingModel;
import com.example.travelguide.models.CONSTANTS;
import com.example.travelguide.models.PostRetrofitModel;
import com.example.travelguide.retrofit.APICONTROLLER;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingConfirmationActivity extends AppCompatActivity {

    BookingModel model;

    ActivityBookingConfirmationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingConfirmationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        model = (BookingModel) intent.getSerializableExtra("model");

        Glide.with(this).load(CONSTANTS.packageImageURL + model.getImageName()).placeholder(R.drawable.picture).into(binding.imageIV);
        binding.packageNameTV.setText(model.getPackageName());
        binding.customerNameTV.setText("Customer Name: " + model.getName());
        binding.customerNidTV.setText("Customer NID: " + model.getNid());
        binding.customerContactTV.setText("Contact Number: " + model.getContact());

        binding.callNowBTN.setOnClickListener(view -> {

            String phone = model.getContact();
            Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
        });

        binding.confirmBTN.setOnClickListener(view -> {

            Call<PostRetrofitModel> confirmBooking = APICONTROLLER.getInstance().getAPI().confirmBooking(model.getCustomerID());
            confirmBooking.enqueue(new Callback<PostRetrofitModel>() {
                @Override
                public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                    if (response.body().getReply().contains("1")) {
                        startActivity(new Intent(BookingConfirmationActivity.this, ConfirmedActivity.class));
                    }
                }

                @Override
                public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

                }
            });

        });


    }
}