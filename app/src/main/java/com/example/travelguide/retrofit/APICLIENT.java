package com.example.travelguide.retrofit;


import com.example.travelguide.models.BookingModel;
import com.example.travelguide.models.PostRetrofitModel;
import com.example.travelguide.models.RunningPackageModel;
import com.example.travelguide.models.ShowPackagesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APICLIENT {

    @GET("adminsite/admin_login.php")
    Call<PostRetrofitModel> loginDone(@Query("code") String student_id);

    @FormUrlEncoded
    @POST("adminsite/create_package.php")
    Call<PostRetrofitModel> packageUplode(
            @Field("image_name") String image_name,
            @Field("image_file") String image_file,
            @Field("package_name") String package_name,
            @Field("person") String person,
            @Field("price") String price,
            @Field("food") String food,
            @Field("arrangement") String arrangement,
            @Field("residence") String residence,
            @Field("guides") String guides,
            @Field("time") String time,
            @Field("contact") String contact,
            @Field("admin_code") String admin_code
    );

    @GET("usersite/show_package.php")
    Call<List<ShowPackagesModel>> showPackages();

    @GET("adminsite/delete_packages.php")
    Call<PostRetrofitModel> delete(@Query("package_code") String package_code);

    @FormUrlEncoded
    @POST("adminsite/update_package.php")
    Call<PostRetrofitModel> update(
            @Field("package_name") String package_name,
            @Field("person") String person,
            @Field("price") String price,
            @Field("food") String food,
            @Field("arrangement") String arrangement,
            @Field("residence") String residence,
            @Field("guides") String guides,
            @Field("time") String time,
            @Field("contact") String contact,

            @Query("package_code") String package_code

    );

    @FormUrlEncoded
    @POST("usersite/confirm_order.php")
    Call<PostRetrofitModel> confirm(
            @Field("name") String name,
            @Field("nid") String nid,
            @Field("package_code") String package_code,
            @Field("contact") String contact,
            @Field("password") String password,
            @Field("image") String image,
            @Field("package_name") String package_name,
            @Field("arrangement") String arrangement
    );

    @GET("adminsite/bookings_list.php")
    Call<List<BookingModel>> reqst();

    @GET("adminsite/confirm_booking.php")
    Call<PostRetrofitModel> confirmBooking(@Query("Customer_ID") String Customer_ID);

    @GET("adminsite/all_confirmed.php")
    Call<List<BookingModel>> allConfirmed();

    @GET("usersite/login.php")
    Call<PostRetrofitModel> loginUser(@Query("contact") String contact,
                                      @Query("password") String password);

    @GET("usersite/running_bookings.php")
    Call<List<RunningPackageModel>> running (
            @Query("contact") String contact
    );







}
