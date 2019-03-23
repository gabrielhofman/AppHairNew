package com.example.apphairnew.web;

import com.example.apphairnew.Service.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiControler {
    public static ApiService CreateController(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.25.237:5000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }
}
