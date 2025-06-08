package com.terabyte.realmdatabaseexample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terabyte.realmdatabaseexample.fragment.PetsToAdoptFragment

class MainViewModel(val application: Application): AndroidViewModel(application) {
    val liveDataCurrentBottomNavFragment = MutableLiveData(PetsToAdoptFragment::class)

    class Factory(private val application: Application): ViewModelProvider.AndroidViewModelFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(application) as T
        }
    }
}