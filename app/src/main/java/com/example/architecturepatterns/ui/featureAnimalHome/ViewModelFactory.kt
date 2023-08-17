package com.example.architecturepatterns.ui.featureAnimalHome


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.architecturepatterns.data.server.ServerApi
import com.example.architecturepatterns.data.server.repository.AnimalRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory (private val api: ServerApi): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(AnimalRepository(api)) as T
        }
        throw IllegalArgumentException("Unknow class name")
    }
}