package com.itis.android.myweatherapp.api

import com.itis.android.myweatherapp.model.WeatherResult
import io.reactivex.Observable
import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getWeatherByName(@Query("q") name: String, @Query("appid") apiKey: String): Observable<WeatherResult>
}
