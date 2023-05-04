package com.example.travelguide.activites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelguide.databinding.ActivityCreatePackageBinding;
import com.example.travelguide.models.CONSTANTS;
import com.example.travelguide.models.PostRetrofitModel;
import com.example.travelguide.retrofit.APICONTROLLER;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePackageActivity extends AppCompatActivity {

    ActivityCreatePackageBinding binding;
    Bitmap bitmap;
    Random random;
    String adminCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreatePackageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        adminCode = intent.getStringExtra("code");

        binding.AddImageBTN.setOnClickListener(view -> {
            addImage();
        });

        binding.confirmBTN.setOnClickListener(view -> {
            try {
                uploadPackage();
            }
            catch (Exception e){
                Toast.makeText(this, "You must upload a picture", Toast.LENGTH_SHORT).show();
            }
            
            
            
        });
        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });


    }

    private void uploadPackage() {
        random = new Random();
        int PhotoName = random.nextInt(1000);


        Call<PostRetrofitModel> uploadPackage = APICONTROLLER.getInstance().getAPI().packageUplode(
                PhotoName + ".jpg",
                ImageFile(bitmap),
                binding.packageNameET.getText().toString(),
                binding.personET.getText().toString(),
                binding.priceET.getText().toString(),
                binding.foodET.getText().toString(),
                binding.arraingmentET.getText().toString(),
                binding.residenceET.getText().toString(),
                binding.guidesET.getText().toString(),
                binding.validityET.getText().toString(),
                binding.contactNumberET.getText().toString(),
                adminCode

        );

        uploadPackage.enqueue(new Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                Toast.makeText(CreatePackageActivity.this, "CREATED", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {
                Toast.makeText(CreatePackageActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void addImage() {

        ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start(CONSTANTS.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CONSTANTS.REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), path);
                binding.addedImage.setImageBitmap(bitmap);
                binding.ImageMark.setVisibility(View.GONE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public String ImageFile(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] ImageToByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(ImageToByte, Base64.DEFAULT);
    }


}