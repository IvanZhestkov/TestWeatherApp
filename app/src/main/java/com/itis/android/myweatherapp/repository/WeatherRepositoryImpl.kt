package com.itis.android.myweatherapp.repository

import com.itis.android.myweatherapp.api.ApiFactory
import com.itis.android.myweatherapp.utils.API_KEY
import com.itis.android.myweatherapp.model.Main
import com.itis.android.myweatherapp.model.WeatherResult
import com.itis.android.myweatherapp.repository.cache.ErrorSingleReadFromCache
import com.itis.android.myweatherapp.repository.cache.RewriteCache
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers

class WeatherRepositoryImpl : WeatherRepository {

    @NonNull
    override fun getTemperature(name: String): Observable<Main>? {
        return ApiFactory.getWeatherService()
                ?.getWeatherByName(name, API_KEY)
                ?.map(WeatherResult::main)
                ?.flatMap { RewriteCache().apply(it) }
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }
}