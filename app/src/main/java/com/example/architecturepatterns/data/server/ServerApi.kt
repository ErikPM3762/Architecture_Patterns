package com.example.architecturepatterns.data.server

import com.example.architecturepatterns.data.model.Animal
import retrofit2.http.GET

interface ServerApi {

    /**
     * We add to our interface the corresponding suspended functions
     * to be able to make the request to services
     */

    @GET("animals.json")
    suspend fun getAnimals():List<Animal>
}