package com.ms.testcode.service

import com.ms.testcode.model.PetOwner
import retrofit2.Call

class DownloadPetOwners(val webService: WebService?) {

    fun getPetOwnersList(): Call<List<PetOwner>>? {
        return webService?.getPetOwnersList()
    }
}