package com.itis.android.myweatherapp

import android.app.Application
import com.itis.android.myweatherapp.api.ApiFactory
import com.itis.android.myweatherapp.repository.RepositoryProvider

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.rx.RealmObservableFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initRealm()
        ApiFactory.recreate()
        RepositoryProvider.init()
    }

    private fun initRealm() {
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
                .rxFactory(RealmObservableFactory())
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(configuration)
    }
}
