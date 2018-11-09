package com.itis.android.myweatherapp.repository.cache

import com.itis.android.myweatherapp.model.Main
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.functions.Function
import io.realm.Realm
import io.realm.RealmObject

class RewriteCache : Function<List<Main?>, Observable<List<Main?>>> {
    override fun apply(list: List<Main?>): Observable<List<Main?>> {
        val realm = Realm.getDefaultInstance()
        realm.refresh()
        realm.executeTransaction { realm ->
            realm.deleteAll()
            list.forEach { item ->
                item?.id = (Math.random() * 1000).toInt()
                realm.insertOrUpdate(item)
            }
        }

        return Observable.fromArray(list)
    }
}

