package com.tarun.sampleweatherapp.data;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {


    private static Retrofit retrofit=null;


    public static Retrofit getRetrofitInstance(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();
        retrofit=new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
