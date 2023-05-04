package com.example.travelguide.activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.travelguide.R;
import com.example.travelguide.adapters.ShowPackagesAdapter;
import com.example.travelguide.databinding.ActivityTouristBinding;
import com.example.travelguide.models.ShowPackagesModel;
import com.example.travelguide.retrofit.APICONTROLLER;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TouristActivity extends AppCompatActivity {
    ActivityTouristBinding binding;
    ShowPackagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTouristBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showPackages();

        binding.menuBTN.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final View customLayout = getLayoutInflater().inflate(R.layout.row_menu_layout, null);

            LinearLayout showList = customLayout.findViewById(R.id.cartBTN);


            showList.setOnClickListener(view1 -> {

           startActivity(new Intent(TouristActivity.this, LoginCustomerAcivity.class));


            });


            builder.setView(customLayout);
            AlertDialog dialog = builder.create();
            dialog.show();


        });


    }

    private void showPackages() {
        binding.recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerview.setHasFixedSize(false);

        Call<List<ShowPackagesModel>> showPackages = APICONTROLLER.getInstance().getAPI().showPackages();
        showPackages.enqueue(new Callback<List<ShowPackagesModel>>() {
            @Override
            public void onResponse(Call<List<ShowPackagesModel>> call, Response<List<ShowPackagesModel>> response) {
                adapter = new ShowPackagesAdapter(response.body(), TouristActivity.this, new ShowPackagesAdapter.OnClick() {
                    @Override
                    public void onClick(ShowPackagesModel model) {
                        Intent intent = new Intent(TouristActivity.this, DetailsPackageActivity.class);
                        intent.putExtra("model", model);
                        startActivity(intent);
                    }
                });

                binding.recyclerview.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<ShowPackagesModel>> call, Throwable t) {

            }
        });
    }
}