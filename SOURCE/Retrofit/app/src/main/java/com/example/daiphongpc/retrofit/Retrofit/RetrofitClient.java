package com.example.daiphongpc.retrofit.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit=null;
    public static Retrofit getClinet(String baseUrl){
        OkHttpClient httpClient=new OkHttpClient.Builder().readTimeout(5000,TimeUnit.MILLISECONDS)
                .writeTimeout(5000,TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .connectTimeout(10000,TimeUnit.MILLISECONDS)
                .build();
        Gson gson=new GsonBuilder().setLenient().create();
        retrofit=new Retrofit.Builder().baseUrl(baseUrl).client(httpClient).addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
