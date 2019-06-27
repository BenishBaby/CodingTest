package com.ms.testcode.service

import com.ms.testcode.model.PetOwner
import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("/people.json")
    fun getPetOwnersList(): Call<List<PetOwner>>
}
