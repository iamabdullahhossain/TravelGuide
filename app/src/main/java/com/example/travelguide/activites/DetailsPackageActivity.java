package com.example.travelguide.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.travelguide.R;
import com.example.travelguide.databinding.ActivityDetailsPackageBinding;
import com.example.travelguide.models.CONSTANTS;
import com.example.travelguide.models.ShowPackagesModel;

public class DetailsPackageActivity extends AppCompatActivity {

    ActivityDetailsPackageBinding binding;
    ShowPackagesModel model;
    String PackageCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsPackageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        model = (ShowPackagesModel) intent.getSerializableExtra("model");


        binding.toolbar.setTitle(model.getPackageName());
        Glide.with(this).load(CONSTANTS.packageImageURL+model.getImageName()).placeholder(R.drawable.picture).into(binding.packageImageIV);
        binding.packageNameTV.setText(model.getPackageName());
        binding.personTV.setText("For "+model.getPerson()+" person");
        binding.priceTV.setText("Price: "+model.getPrice()+" taka");
        binding.foodTV.setText("Food: "+model.getFood());
        binding.arraingmentTV.setText("Arrangement: "+model.getArrangement());
        binding.residenceTV.setText("Residence: "+model.getResidence());
        binding.guidesTV.setText("Guides: "+model.getGuides()+" person as instructor.");
        binding.validityTV.setText("Days: "+model.getTime());
        binding.contactNumberTV.setText("Call Us: "+model.getContact());
        binding.confirmBTN.setText("Avail This Package Only On "+model.getPrice()+" taka");
        PackageCode = model.getPackageCode();



        binding.confirmBTN.setOnClickListener(view -> {
            try {
                Intent intent1 = new Intent(DetailsPackageActivity.this, CustomerInformationActivity.class);
                intent1.putExtra("model", model);
                startActivity(intent1);
            }catch (Exception e){
                Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            }

            binding.toolbar.setNavigationOnClickListener(view1 -> {
                finish();
            });




        });






    }
}