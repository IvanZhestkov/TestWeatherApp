package com.itis.android.myweatherapp.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Main(@PrimaryKey var id: Int = 0,

                var temp: Double? = 0.toDouble(),

                var pressure: Int? = 0,

                var himidity: Int? = 0,

                var temp_min: Double? = 0.toDouble(),

                var temp_max: Double? = 0.toDouble()) : RealmObject()
