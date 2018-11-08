package com.itis.android.myweatherapp.utils

import java.math.RoundingMode

fun fromKtoC(temp: Double?) : String {
    return (temp?.minus(273.15))?.toBigDecimal()?.setScale(1, RoundingMode.HALF_UP)?.toDouble().toString()
}