package com.example.weatherapp.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Example2 {
    @SerializedName("weather")
    private Main weather;

    public Main getWeather() {
        return weather;
    }

    public void setWeather(Main weather) {
        this.weather = weather;
    }
}
