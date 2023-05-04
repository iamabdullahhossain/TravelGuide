package com.example.travelguide.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.travelguide.R;
import com.example.travelguide.databinding.ActivityUpdatePackagesBinding;
import com.example.travelguide.models.CONSTANTS;
import com.example.travelguide.models.PostRetrofitModel;
import com.example.travelguide.models.ShowPackagesModel;
import com.example.travelguide.retrofit.APICONTROLLER;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePackagesActivity extends AppCompatActivity {

    ActivityUpdatePackagesBinding binding;
    ShowPackagesModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdatePackagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        model = (ShowPackagesModel) intent.getSerializableExtra("model");

        Glide.with(this).load(CONSTANTS.packageImageURL+model.getImageName()).placeholder(R.drawable.picture).into(binding.addedImage);
        binding.ImageMark.setVisibility(View.GONE);
        binding.packageNameET.setText(model.getPackageName());
        binding.personET.setText(model.getPerson());
        binding.priceET.setText(model.getPrice());
        binding.foodET.setText(model.getFood());
        binding.arraingmentET.setText(model.getArrangement());
        binding.residenceET.setText(model.getResidence());
        binding.guidesET.setText(model.getGuides());
        binding.validityET.setText(model.getTime());
        binding.contactNumberET.setText(model.getContact());


        binding.confirmBTN.setOnClickListener(view -> {

            updateInformation(model);
        });


    }

    private void updateInformation(ShowPackagesModel model) {
        Call<PostRetrofitModel> update = APICONTROLLER.getInstance().getAPI().update(

                model.getPackageName(),
                model.getPerson(),
                model.getPrice(),
                model.getFood(),
                model.getArrangement(),
                model.getResidence(),
                model.getGuides(),
                model.getTime(),
                model.getContact(),

                model.getPackageCode()
        );
        
        update.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                if (response.body().getReply().contains("1")){
                    Toast.makeText(UpdatePackagesActivity.this, "UPDATED", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UpdatePackagesActivity.this, "UPLOAD FAILED", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {
                Toast.makeText(UpdatePackagesActivity.this, "Connection ERROR! PROBLEM: "+t, Toast.LENGTH_SHORT).show();
            }
        });
        
        
    }


}