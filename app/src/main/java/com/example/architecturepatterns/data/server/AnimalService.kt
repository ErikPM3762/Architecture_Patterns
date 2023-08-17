package com.example.architecturepatterns.data.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Here we have the base URL for the service request
 * Here we are going to configure retrofit
 */

object AnimalService {
    const val BASE_URL = "https://raw.githubusercontent.com/CatalinStefan/animalApi/master/"


    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ServerApi = getRetrofit().create(ServerApi::class.java)
}