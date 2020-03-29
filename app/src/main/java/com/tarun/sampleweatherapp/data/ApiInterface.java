package com.tarun.sampleweatherapp.data;


import com.tarun.sampleweatherapp.pojo.currentTemp.CityTemp;
import com.tarun.sampleweatherapp.pojo.futureTemp.FutureTemp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    //    @GET("weather?q={cityName}&APPID={appID}")
    @GET("weather")
    Call<CityTemp> getWeatherDetails(@Query("q") String cityName, @Query("APPID") String appID);

    //    Call<CityTemp> getWeatherDetails(@Path("cityName") String cityName,@Path("appID") String appID);
    @GET("forecast/daily")
    Call<FutureTemp> getFutureWeatherDetails(@Query("q") String cityName, @Query("cnt") String count, @Query("appid") String appID);

}
