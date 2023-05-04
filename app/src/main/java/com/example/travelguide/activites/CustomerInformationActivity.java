package com.example.travelguide.activites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelguide.databinding.ActivityCustomerInformationBinding;
import com.example.travelguide.models.PostRetrofitModel;
import com.example.travelguide.models.ShowPackagesModel;
import com.example.travelguide.retrofit.APICONTROLLER;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerInformationActivity extends AppCompatActivity {
    ActivityCustomerInformationBinding binding;

    ShowPackagesModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        model = (ShowPackagesModel) intent.getSerializableExtra("model");

        binding.confirmBTN.setOnClickListener(view -> {
            if (binding.customerNameET.getText().toString().isEmpty() && binding.nidET.getText().toString().isEmpty() && binding.contactET.getText().toString().isEmpty() && binding.passwordET.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please Provide Your Informations First!", Toast.LENGTH_SHORT).show();
            } else {
                confirmOrder(model.getPackageCode(), model.getImageName(), model.getPackageName(), model.getArrangement());
            }

        });

        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });

    }

    private void confirmOrder(String package_code, String imageName, String packageName, String arrangement) {

        Call<PostRetrofitModel> confirmOrder = APICONTROLLER.getInstance().getAPI().confirm(binding.customerNameET.getText().toString(),
                binding.nidET.getText().toString(),
                package_code, binding.contactET.getText().toString(),
                binding.passwordET.getText().toString(),
                imageName, packageName, arrangement);
        confirmOrder.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                Toast.makeText(CustomerInformationActivity.this, "Order Confirmed", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CustomerInformationActivity.this, ConfirmedActivity.class));
            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {
                Toast.makeText(CustomerInformationActivity.this, "Check your internet connection " + package_code, Toast.LENGTH_SHORT).show();
            }
        });


    }
}