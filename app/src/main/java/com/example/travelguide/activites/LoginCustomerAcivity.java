package com.example.travelguide.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.travelguide.databinding.ActivityLoginCustomerAcivityBinding;
import com.example.travelguide.models.PostRetrofitModel;
import com.example.travelguide.retrofit.APICONTROLLER;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginCustomerAcivity extends AppCompatActivity {
    ActivityLoginCustomerAcivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginCustomerAcivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginBTN.setOnClickListener(view -> {
            if (binding.contactNumberET.getText().toString().isEmpty() && binding.passwordET.getText().toString().isEmpty()){
                Toast.makeText(this, "Please provide all the information first", Toast.LENGTH_SHORT).show();
            }
            else {
                login();
            }

        });


    }

    private void login() {

        Call<PostRetrofitModel> login = APICONTROLLER.getInstance().getAPI().loginUser(
                binding.contactNumberET.getText().toString(),
                binding.passwordET.getText().toString()
        );

        login.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                if (response.body().getReply().contains("1111")){
                    Intent intent = new Intent(LoginCustomerAcivity.this, AllActivatedPackageActivity.class);
                    intent.putExtra("contact" , binding.contactNumberET.getText().toString());
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

            }
        });



    }
}