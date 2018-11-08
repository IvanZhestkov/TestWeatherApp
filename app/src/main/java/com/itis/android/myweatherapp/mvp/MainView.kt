package com.itis.android.myweatherapp.mvp

import com.arellomobile.mvp.MvpView
import com.itis.android.myweatherapp.model.City
import com.itis.android.myweatherapp.model.Main
import io.reactivex.annotations.NonNull

interface MainView : MvpView {

    fun showItems(@NonNull list: ArrayList<Main?>)

    fun handleError(error: Throwable)

    fun addMoreItems(items: List<City>)

    fun showRefreshing()

    fun showLoading()

    fun hideLoading()

    fun hideRefreshing()
}