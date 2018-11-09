package com.itis.android.myweatherapp.repository.cache

import com.itis.android.myweatherapp.model.Main
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.functions.Function
import io.realm.Realm
import io.realm.RealmObject

class RewriteCache : Function<Main, Observable<Main>?> {
    override fun apply(t: Main): Observable<Main>? {
        Realm.getDefaultInstance().executeTransaction { realm ->
            realm.deleteAll()
            realm.insertOrUpdate(t)
        }
        return Observable.just(t)
    }
}

