package com.terabyte.realmdatabaseexample.realm

import io.realm.Realm
import io.realm.RealmObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object RealmHelper {

    fun<T : RealmObject> createRealmObject(realmObject: T, listener: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO) {
                val realm = Realm.getDefaultInstance()
                realm.executeTransaction {
                    it.copyToRealm(realmObject)
                }
                return@async
            }
            deferred.await()
            listener()
        }
    }

    fun<T: RealmObject> getAll(modelClass: Class<T>, listener: (List<T>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO) {
                val realm = Realm.getDefaultInstance()
                val result = realm.where(modelClass).findAll()
                return@async result.toList()
            }
            listener(deferred.await())
        }
    }
}