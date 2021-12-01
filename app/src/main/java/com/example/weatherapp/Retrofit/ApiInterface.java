package com.example.weatherapp.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?appid=c8b68fe3372e2f267738dc95e16411f9&units=metric")
    Call<Example> getWeatherData(@Query("q") String name);
}
