package com.itis.android.myweatherapp.repository

import com.itis.android.myweatherapp.model.Main
import io.reactivex.Observable


interface WeatherRepository {
    fun getTemperature(name: String): Observable<Main>?
}