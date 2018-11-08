package com.itis.android.myweatherapp.model

import io.realm.annotations.PrimaryKey

open class WeatherResult(@PrimaryKey var id: Int = 0, var main: Main)