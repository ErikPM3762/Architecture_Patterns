package com.example.architecturepatterns.data.server.repository

import com.example.architecturepatterns.data.server.ServerApi

class AnimalRepository(private val api: ServerApi) {
    suspend fun getAnimals()= api.getAnimals()
}