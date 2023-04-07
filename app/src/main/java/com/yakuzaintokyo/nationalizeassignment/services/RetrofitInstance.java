package com.yakuzaintokyo.nationalizeassignment.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://api.nationalize.io/";

    public static NationalityService getService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return  retrofit.create(NationalityService.class);
    }
}
