package com.example.architecturepatterns.ui.featureAnimalHome

import com.example.architecturepatterns.data.model.Animal

sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class Animals(val animals: List<Animal>) :MainState()
    data class Error(val error: String?) : MainState()
}