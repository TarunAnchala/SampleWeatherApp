package com.tarun.sampleweatherapp.pojo;

public class FutureData {

    private String day;
    private String temp;

    public FutureData(String day, String temp) {
        this.day = day;
        this.temp = temp;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
