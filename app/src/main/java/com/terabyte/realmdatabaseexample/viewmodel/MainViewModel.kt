package com.terabyte.realmdatabaseexample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terabyte.realmdatabaseexample.R
import com.terabyte.realmdatabaseexample.realm.OwnerModel
import com.terabyte.realmdatabaseexample.realm.RealmHelper

class MainViewModel(val myApplication: Application): AndroidViewModel(myApplication) {
    val liveDataCurrentMenuItemId = MutableLiveData(R.id.menuItemPetsToAdopt)
    val liveDataOwners = MutableLiveData<List<OwnerModel>>(listOf())

    init {
        updateOwnersList()
    }

    fun updateOwnersList() {
        RealmHelper.getAll(OwnerModel::class.java) {
            liveDataOwners.value = it
        }
    }

    class Factory(private val application: Application): ViewModelProvider.AndroidViewModelFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(application) as T
        }
    }
}