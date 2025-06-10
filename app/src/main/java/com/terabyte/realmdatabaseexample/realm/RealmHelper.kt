package com.terabyte.realmdatabaseexample.realm

import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmObject
import io.realm.kotlin.delete
import io.realm.kotlin.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object RealmHelper {

    fun<T : RealmObject> createRealmObject(realmObject: T, listener: () -> Unit) {
        val realm = Realm.getDefaultInstance()
        val transaction = Realm.Transaction {
            it.copyToRealm(realmObject)
        }
        val onSuccess = Realm.Transaction.OnSuccess {
            listener()
        }
        realm.executeTransactionAsync(transaction, onSuccess)
    }

    fun<T: RealmObject> deleteRealmObject(id: String, modelClass: Class<T>, listener: () -> Unit) {
        val realm = Realm.getDefaultInstance()

        val transaction = Realm.Transaction {
            val realmObject = it.where(modelClass).equalTo("id", id).findFirst()
            //to avoid twice deletions of realmObject
            if (realmObject != null && realmObject.isValid) {
                realmObject.deleteFromRealm()
            }
        }
        val onSuccess = Realm.Transaction.OnSuccess {
            listener()
        }
        realm.executeTransactionAsync(transaction, onSuccess)
    }

    fun<T: RealmObject> getAll(modelClass: Class<T>, listener: (List<T>) -> Unit) {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(modelClass).findAllAsync()
        result.addChangeListener( RealmChangeListener {
            if (it.isNotEmpty()) {
                listener(it)
            }
        })
    }

    fun<T: RealmObject> setChangeListenerForAll(modelClass: Class<T>, onChange: (List<T>) -> Unit) {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(modelClass).findAllAsync()
        result.addChangeListener( RealmChangeListener {
            if (it.isNotEmpty()) {
                it.addChangeListener( RealmChangeListener {
                    onChange(it)
                })
            }
        })
    }



}