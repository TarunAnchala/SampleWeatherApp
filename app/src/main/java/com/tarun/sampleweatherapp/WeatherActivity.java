package com.tarun.sampleweatherapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tarun.sampleweatherapp.data.ApiInterface;
import com.tarun.sampleweatherapp.data.WeatherViewModel;
import com.tarun.sampleweatherapp.pojo.FutureData;
import com.tarun.sampleweatherapp.pojo.currentTemp.CityTemp;
import com.tarun.sampleweatherapp.pojo.futureTemp.FutureTemp;
import com.tarun.sampleweatherapp.pojo.futureTemp.List;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class WeatherActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "WeatherActivity";
    private TextView temperatureView;
    private TextView cityNameView;
    private WeatherViewModel weatherViewModel;
    private ProgressBar progressBar;
    private ImageView tempLogo;
    private RecyclerView recyclerView;
    private ImageView upArrow;
    private ImageView downArrow;
    private TextView todayText;
    private RelativeLayout scrollUpDownLayout;
    private ExecutorService executorService= Executors.newSingleThreadExecutor();


    private ArrayList<FutureData> futureDataArrayList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);
        temperatureView = findViewById(R.id.temperatureView);
        cityNameView = findViewById(R.id.cityName);
        tempLogo = findViewById(R.id.tempLogo);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        scrollUpDownLayout=findViewById(R.id.scrollUpLayout);
        upArrow=findViewById(R.id.upArrow);
        todayText=findViewById(R.id.todayText);
        downArrow=findViewById(R.id.downArrow);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE");
        Date date = new Date();
        FutureWeatherAdapter futureWeatherAdapter=new FutureWeatherAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(futureWeatherAdapter);
        progressBar.setVisibility(View.VISIBLE);
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        String cityName = getIntent().getStringExtra("cityName");
        upArrow.setOnClickListener(this);
        downArrow.setOnClickListener(this);


        weatherViewModel.getTempLiveData(cityName).observe(this, new Observer<CityTemp>() {
            @Override
            public void onChanged(CityTemp cityTemp) {
                Log.e(TAG, "onChanged: called ----");
                if (cityTemp != null) {
                    String temp = cityTemp.getMain().getTemp();
                    if (temp != null && !temp.equals("")) {
                        progressBar.setVisibility(View.GONE);
                        todayText.setVisibility(View.VISIBLE);
                        cityNameView.setText(cityName);
                        float temperature = Float.parseFloat(temp) - 273;
                        if (temperature > 23) {
                            Glide.with(WeatherActivity.this).load(R.drawable.sun).into(tempLogo);
                        } else {
                            Glide.with(WeatherActivity.this).load(R.drawable.rain).into(tempLogo);

                        }
                        temperatureView.setText(Math.round(temperature) + "\u2103");
                    }
                }
            }
        });


        weatherViewModel.getFutureTempLiveData(cityName).observe(this, new Observer<FutureTemp>() {
            @Override
            public void onChanged(FutureTemp futureTemp) {
                if (futureTemp != null && futureTemp.getList() != null && futureTemp.getList().length > 0) {
                    scrollUpDownLayout.setVisibility(View.VISIBLE);
                    upArrow.setVisibility(View.VISIBLE);
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < futureTemp.getList().length; i++) {
                                List weaterDetails = futureTemp.getList()[i];
                                if (weaterDetails != null && weaterDetails.getTemp() != null) {
                                    float temp = Float.parseFloat(weaterDetails.getTemp().getDay()) - 273;
                                    String temperature=String.valueOf(Math.round(temp));

                                    Calendar c = Calendar.getInstance();
                                    c.add(Calendar.DATE, i+1);  // number of days to add
                                    String dayOfTheWeek  = simpleDateFormat.format(c.getTime());
                                    FutureData futureData=new FutureData(dayOfTheWeek,temperature);
                                    futureDataArrayList.add(futureData);
                                }
                            }
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    futureWeatherAdapter.setData(futureDataArrayList);
                                }
                            });

                        }
                    });


                }
            }
        });
    }

    public void handleAnimation(){

    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick:called =="  );
        TranslateAnimation animate=null;
        if(v.getId()==R.id.upArrow){
            Log.e(TAG, "onClick: inside if" );
            recyclerView.setVisibility(View.VISIBLE);
            downArrow.setVisibility(View.VISIBLE);
            upArrow.setVisibility(View.INVISIBLE);
             animate = new TranslateAnimation(
                    0,
                    0,
                    recyclerView.getHeight(),
                    0);


        }
        else if(v.getId()==R.id.downArrow){
            Log.e(TAG, "onClick: inside else" );
            recyclerView.setVisibility(View.GONE);
            downArrow.setVisibility(View.INVISIBLE);
            upArrow.setVisibility(View.VISIBLE);
//            view.setVisibility(View.INVISIBLE);
             animate = new TranslateAnimation(
                    0,
                    0,
                    0,
                    recyclerView.getHeight());

        }
        if(animate!=null) {
            animate.setDuration(500);
            animate.setFillAfter(true);
            recyclerView.startAnimation(animate);
        }
    }
}
