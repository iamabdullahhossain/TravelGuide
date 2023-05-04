package com.example.travelguide.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APICONTROLLER {
    private static final String BASE_URL = "http://192.168.0.104/travelguide/";

    private static APICONTROLLER apicontroller;
    private static Retrofit retrofit;

    public APICONTROLLER() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized APICONTROLLER getInstance() {
        if (apicontroller == null)
            apicontroller = new APICONTROLLER();

        return apicontroller;

    }

    public APICLIENT getAPI() {
        return retrofit.create(APICLIENT.class);
    }

}
