package com.itis.android.myweatherapp.mvp

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.itis.android.myweatherapp.model.Main
import com.itis.android.myweatherapp.repository.RepositoryProvider
import com.itis.android.myweatherapp.utils.Five
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable


@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadCity()
    }

    @SuppressLint("CheckResult")
    fun loadCity(progressType: ProgressType = ProgressType.ListProgress) {
        Observable.zip(
                getTemperature("Arsk"), getTemperature("Zelenodolsk"),
                getTemperature("Volzsk"), getTemperature("Kozlovka"),
                getTemperature("Mamadysh"), Five.zipFunc())
                ?.doOnSubscribe { onLoadingStart(progressType) }
                ?.doAfterTerminate { onLoadingFinish(progressType) }
                ?.subscribe({ temp ->
                    temp?.let {
                        val list: ArrayList<Main?> = arrayListOf(it.first, it.second, it.third, it.fourth, it.fiveth)
                        viewState.showItems(list)
                    }

                }, { error -> viewState.handleError(error) })
    }

    private fun getTemperature(name: String): Observable<Main>? {
        return RepositoryProvider.provideWeatherRepository()
                ?.getTemperature(name)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun showProgress(progressType: ProgressType) {
        when (progressType) {
            ProgressType.Refreshing -> viewState.showRefreshing()
            ProgressType.ListProgress -> viewState.showLoading()
        }
    }

    private fun hideProgress(progressType: ProgressType) {
        when (progressType) {
            ProgressType.Refreshing -> viewState.hideRefreshing()
            ProgressType.ListProgress -> viewState.hideLoading()
        }
    }

    fun loadRefresh() {
        loadCity(ProgressType.Refreshing)
    }

    private fun onLoadingStart(progressType: ProgressType) {
        showProgress(progressType)
    }

    private fun onLoadingFinish(progressType: ProgressType) {
        hideProgress(progressType)
    }

    enum class ProgressType {
        Refreshing, ListProgress
    }
}