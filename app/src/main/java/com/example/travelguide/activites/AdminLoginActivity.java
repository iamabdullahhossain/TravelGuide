package com.example.travelguide.activites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelguide.databinding.ActivityAdminLoginBinding;
import com.example.travelguide.models.PostRetrofitModel;
import com.example.travelguide.retrofit.APICONTROLLER;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginActivity extends AppCompatActivity {

    ActivityAdminLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.confirmBTN.setOnClickListener(view -> {

            adminLogin(binding.confirmationET.getText().toString());

        });


    }

    private void adminLogin(String code) {

        Call<PostRetrofitModel> loginadmin = APICONTROLLER.getInstance().getAPI().loginDone(code);
        loginadmin.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                if (response.body().getReply().contains("1111")) {
                    Intent intent = new Intent(AdminLoginActivity.this, HomeScreenAdminActivity.class);
                    intent.putExtra("code", code);
                    startActivity(intent);
                    finish();
                    binding.confirmationET.getText().clear();

                } else {
                    Toast.makeText(AdminLoginActivity.this, "Code didn't match!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {
                Toast.makeText(AdminLoginActivity.this, "Check internet connection", Toast.LENGTH_SHORT).show();
            }
        });

    }
}