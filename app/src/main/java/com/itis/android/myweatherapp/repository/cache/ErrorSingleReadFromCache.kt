package com.itis.android.myweatherapp.repository.cache

import com.itis.android.myweatherapp.model.Main
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults

class ErrorSingleReadFromCache {

    fun getMains(): Observable<List<Main?>> {
        val realm = Realm.getDefaultInstance()
        return Observable.fromArray(realm.where(Main::class.java).findAll())
    }
}
