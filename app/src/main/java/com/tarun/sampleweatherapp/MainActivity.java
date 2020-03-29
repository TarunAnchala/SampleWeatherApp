package com.tarun.sampleweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText selectCityEditText;
    private Button fetchWeatherButton;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchWeatherButton=findViewById(R.id.fetchWeatherButton);
        selectCityEditText=findViewById(R.id.selectCityEditText);
        fetchWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectCityEditText.getText() != null && selectCityEditText.getText().toString().trim().length() > 0) {
                    String cityName=selectCityEditText.getText().toString().trim();
                    Log.e(TAG, "onClick:city name === "+cityName );
                    Intent intent=new Intent(MainActivity.this,WeatherActivity.class);
                    intent.putExtra("cityName",cityName);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Please enter valid city name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectCityEditText.setText("");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
