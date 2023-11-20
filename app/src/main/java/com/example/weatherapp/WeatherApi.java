package com.example.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("data/2.5/weather")
    Call<WeatherResponse> getCurrentWeather(
            @Query("q") String cityName,
            @Query("units") String units,
            @Query("lang") String lang,
            @Query("appid") String apiKey
    );
}
