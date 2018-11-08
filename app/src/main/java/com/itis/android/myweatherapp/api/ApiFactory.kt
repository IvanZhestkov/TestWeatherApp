package com.itis.android.myweatherapp.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {
    companion object {
        private const val WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/"

        private var weatherService: WeatherService? = null

        fun getWeatherService(): WeatherService? {
            if (weatherService == null) {
                weatherService = buildRetrofit().create(WeatherService::class.java)
            }
            return weatherService
        }

        fun recreate() {
            weatherService = buildRetrofit().create(WeatherService::class.java)
        }

        private fun buildRetrofit(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(WEATHER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }
}