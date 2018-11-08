package com.itis.android.myweatherapp.repository

import android.support.annotation.MainThread

class RepositoryProvider {

    companion object {
        private var weatherRepository: WeatherRepository? = null

        fun provideWeatherRepository(): WeatherRepository? {
            if (weatherRepository == null) {
                weatherRepository = WeatherRepositoryImpl()
            }
            return weatherRepository
        }

        fun setWeatherRepository(repository: WeatherRepository) {
            weatherRepository = repository
        }

        @MainThread
        fun init() {
            weatherRepository = WeatherRepositoryImpl()
        }
    }
}