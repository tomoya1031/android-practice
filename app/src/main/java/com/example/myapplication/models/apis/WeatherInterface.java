package com.example.myapplication.models.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {
    @GET("weather")
    Call<WeatherNews> getWeatherByAppId(@Query("q") String name,
                                        @Query("appid") String apiKey);
}
