package com.example.travelguide.activites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.travelguide.adapters.AllCreatedPackagesAdapter;
import com.example.travelguide.databinding.ActivityAllCreatedPackageBinding;
import com.example.travelguide.models.ShowPackagesModel;
import com.example.travelguide.retrofit.APICONTROLLER;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCreatedPackageActivity extends AppCompatActivity {

    ActivityAllCreatedPackageBinding binding;
    AllCreatedPackagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllCreatedPackageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showAllPackages();
        setToolbar();


    }

    private void setToolbar() {
        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
    }

    private void showAllPackages() {
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerview.setHasFixedSize(false);

        Call<List<ShowPackagesModel>> getPackages = APICONTROLLER.getInstance().getAPI().showPackages();
        getPackages.enqueue(new Callback<List<ShowPackagesModel>>() {
            @Override
            public void onResponse(Call<List<ShowPackagesModel>> call, Response<List<ShowPackagesModel>> response) {
                adapter = new AllCreatedPackagesAdapter(response.body(), AllCreatedPackageActivity.this, new AllCreatedPackagesAdapter.onClick() {


                    @Override
                    public void onClick(ShowPackagesModel model, List<ShowPackagesModel> list, int position) {
                        Intent intent = new Intent(AllCreatedPackageActivity.this, UpdatePackagesActivity.class);
                        intent.putExtra("model", model);
                        startActivity(intent);


                    }

                    @Override
                    public void onDelete(List<ShowPackagesModel> list, int position, AllCreatedPackagesAdapter.ViewHolder holder, ShowPackagesModel model) {

                        String url = "http://192.168.0.104/travelguide/adminsite/delete_packages.php?package_code=" + model.getPackageCode();
                        RequestQueue requestQueue = Volley.newRequestQueue(AllCreatedPackageActivity.this);
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(AllCreatedPackageActivity.this, "Package Removed", Toast.LENGTH_SHORT).show();
                                list.remove(holder.getAdapterPosition());
                                adapter.notifyItemRemoved(holder.getAdapterPosition());


                            }
                        }, new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                        requestQueue.add(stringRequest);


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