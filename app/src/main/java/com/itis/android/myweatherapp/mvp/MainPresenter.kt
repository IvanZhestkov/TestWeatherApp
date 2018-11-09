package com.itis.android.myweatherapp.mvp

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.itis.android.myweatherapp.model.Main
import com.itis.android.myweatherapp.repository.RepositoryProvider
import com.itis.android.myweatherapp.repository.cache.ErrorSingleReadFromCache
import com.itis.android.myweatherapp.repository.cache.RewriteCache
import com.itis.android.myweatherapp.utils.Five
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm
import java.util.concurrent.TimeUnit

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadCity()
    }

    fun loadCity(progressType: ProgressType = ProgressType.ListProgress) {
        compositeDisposable.addAll(Observable.zip(
                getTemperature("Arsk"), getTemperature("Zelenodol'sk"),
                getTemperature("Volzhsk"), getTemperature("Kozlovka"),
                getTemperature("Mamadysh"), Five.zipFunc())
                ?.flatMap { RewriteCache().apply(arrayListOf(it.first, it.second, it.third, it.fourth, it.fiveth)) }
                ?.onErrorResumeNext(ErrorSingleReadFromCache().getMains())
                ?.doOnSubscribe { onLoadingStart(progressType) }
                ?.doAfterTerminate { onLoadingFinish(progressType) }
                ?.subscribe({ temp ->
                    temp?.let {
                        viewState.showItems(it)
                    }

                }, { error -> viewState.handleError(error) }))
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