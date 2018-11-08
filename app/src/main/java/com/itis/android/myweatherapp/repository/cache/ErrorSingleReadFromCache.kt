package com.itis.android.myweatherapp.repository.cache

import com.itis.android.myweatherapp.model.Main
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults

class ErrorSingleReadFromCache : Function<Throwable, Observable<Main>> {

    override fun apply(@io.reactivex.annotations.NonNull throwable: Throwable): Observable<Main> {
        val realm = Realm.getDefaultInstance()
        val results = realm.where(Main::class.java).findAll()
        return Observable.just(realm.copyFromRealm(results.first()))
    }
}
