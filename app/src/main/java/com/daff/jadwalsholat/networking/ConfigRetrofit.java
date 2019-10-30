package com.daff.jadwalsholat.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {

    private static String BASE_URL = "http://muslimsalat.com/";

    public static Retrofit getAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
