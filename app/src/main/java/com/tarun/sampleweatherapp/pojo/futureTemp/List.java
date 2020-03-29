package com.tarun.sampleweatherapp.pojo.futureTemp;

public class List
{
    private String dt;

    private String sunrise;

    private Temp temp;

    private String sunset;

    private String deg;

    private Weather[] weather;

    private String humidity;

    private String pressure;

    private String clouds;

    private Feels_like feels_like;

    private String speed;

    public String getDt ()
    {
        return dt;
    }

    public void setDt (String dt)
    {
        this.dt = dt;
    }

    public String getSunrise ()
    {
        return sunrise;
    }

    public void setSunrise (String sunrise)
    {
        this.sunrise = sunrise;
    }

    public Temp getTemp ()
    {
        return temp;
    }

    public void setTemp (Temp temp)
    {
        this.temp = temp;
    }

    public String getSunset ()
    {
        return sunset;
    }

    public void setSunset (String sunset)
    {
        this.sunset = sunset;
    }

    public String getDeg ()
    {
        return deg;
    }

    public void setDeg (String deg)
    {
        this.deg = deg;
    }

    public Weather[] getWeather ()
    {
        return weather;
    }

    public void setWeather (Weather[] weather)
    {
        this.weather = weather;
    }

    public String getHumidity ()
    {
        return humidity;
    }

    public void setHumidity (String humidity)
    {
        this.humidity = humidity;
    }

    public String getPressure ()
    {
        return pressure;
    }

    public void setPressure (String pressure)
    {
        this.pressure = pressure;
    }

    public String getClouds ()
    {
        return clouds;
    }

    public void setClouds (String clouds)
    {
        this.clouds = clouds;
    }

    public Feels_like getFeels_like ()
    {
        return feels_like;
    }

    public void setFeels_like (Feels_like feels_like)
    {
        this.feels_like = feels_like;
    }

    public String getSpeed ()
    {
        return speed;
    }

    public void setSpeed (String speed)
    {
        this.speed = speed;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dt = "+dt+", sunrise = "+sunrise+", temp = "+temp+", sunset = "+sunset+", deg = "+deg+", weather = "+weather+", humidity = "+humidity+", pressure = "+pressure+", clouds = "+clouds+", feels_like = "+feels_like+", speed = "+speed+"]";
    }
}

