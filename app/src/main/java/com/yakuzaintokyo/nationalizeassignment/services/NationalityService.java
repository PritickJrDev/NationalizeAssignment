package com.yakuzaintokyo.nationalizeassignment.services;


import com.yakuzaintokyo.nationalizeassignment.model.Country;
import com.yakuzaintokyo.nationalizeassignment.model.National;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NationalityService {

    //https://api.nationalize.io/?name=

    @GET("?name=")
    Call<National> getNationality(@Query("name") String name);

}
