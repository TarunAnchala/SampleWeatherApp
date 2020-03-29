package com.tarun.sampleweatherapp.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tarun.sampleweatherapp.pojo.currentTemp.CityTemp;
import com.tarun.sampleweatherapp.pojo.futureTemp.FutureTemp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {

    private static final String TAG = "WeatherViewModel";
    private MutableLiveData<CityTemp> tempLiveData = new MutableLiveData<>();
    private MutableLiveData<FutureTemp> futureTempLiveData = new MutableLiveData<>();
    private ApiInterface apiInterface;
    private String API_KEY = "09f47ff9b4456c47e672bfcfdf32eafe";
    private String API_KEY2 = "8118ed6ee68db2debfaaa5a44c832918";


    public WeatherViewModel() {
        Log.e(TAG, "WeatherViewModel: constructor called ");
        apiInterface = RetrofitService.getRetrofitInstance().create(ApiInterface.class);
    }

    public MutableLiveData<CityTemp> getTempLiveData(String cityName) {


        Call<CityTemp> weatherDetailsCall = apiInterface.getWeatherDetails(cityName, API_KEY);
        weatherDetailsCall.enqueue(new Callback<CityTemp>() {
            @Override
            public void onResponse(Call<CityTemp> call, Response<CityTemp> response) {
                Log.e(TAG, "onResponse: called ====" + response.body());
                if (response.isSuccessful() && response.body() != null && response.body().getMain() != null) {

                    tempLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<CityTemp> call, Throwable t) {
                Log.e(TAG, "onFailure: called ====" + t.getMessage());
            }
        });
        return tempLiveData;
    }


    public MutableLiveData<FutureTemp> getFutureTempLiveData(String cityName) {


        Call<FutureTemp> weatherDetailsCall = apiInterface.getFutureWeatherDetails(cityName, "6",API_KEY2);
        weatherDetailsCall.enqueue(new Callback<FutureTemp>() {
            @Override
            public void onResponse(Call<FutureTemp> call, Response<FutureTemp> response) {

                if (response.isSuccessful() && response.body() != null && response.body().getList() != null) {
                    futureTempLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FutureTemp> call, Throwable t) {
                Log.e(TAG, "onFailure: called ====" + t.getMessage());
            }
        });
        return futureTempLiveData;
    }
}
