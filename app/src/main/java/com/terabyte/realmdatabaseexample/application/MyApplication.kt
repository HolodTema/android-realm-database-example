package com.terabyte.realmdatabaseexample.application

import android.app.Application
import com.terabyte.realmdatabaseexample.util.REALM_SCHEMA
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val realmConfiguration = RealmConfiguration.Builder()
            .name("RealmDatabase.db")
            .schemaVersion(REALM_SCHEMA)
//            .allowWritesOnUiThread(true)
//            .allowQueriesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}