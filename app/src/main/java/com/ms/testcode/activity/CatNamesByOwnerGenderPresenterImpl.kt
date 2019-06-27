package com.ms.testcode.activity

import com.ms.testcode.model.Pet
import com.ms.testcode.model.PetOwner
import com.ms.testcode.service.DownloadPetOwners
import com.ms.testcode.service.RetrofitClientInstance
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatNamesByOwnerGenderPresenterImpl(val catNamesByOwnerGenderView: CatNamesByOwnerGenderView): CatNamesByOwnerGenderPresenter {

    override fun getPetOwnerDetails() {
        val petOwnersList = DownloadPetOwners(RetrofitClientInstance().getClientInstance()).getPetOwnersList()

      petOwnersList?.enqueue(object : Callback<List<PetOwner>> {
            override fun onResponse(call: Call<List<PetOwner>>, response: Response<List<PetOwner>>) {
                catNamesByOwnerGenderView.hideLoading()
                catNamesByOwnerGenderView.showPetNamesListByOwnerGender(sortArray(response.body() as ArrayList<PetOwner>))
            }

            override fun onFailure(call: Call<List<PetOwner>>, t: Throwable) {
                catNamesByOwnerGenderView.hideLoading()
                catNamesByOwnerGenderView.showAlertOnServiceUnavailability()
             }
        })


    }

    private fun sortArray(petOwnersList:ArrayList<PetOwner>): ArrayList<String> {

        val petOwnersListGender  = petOwnersList.groupBy { it.gender }
        val malePetOwnersList = petOwnersListGender["Male"]
        val femalePetOwnersList = petOwnersListGender["Female"]
        val maleOwnerPetList = ArrayList<Pet>()
        for(petOwner:PetOwner in malePetOwnersList!!) {
            if(petOwner.pets != null) {
                maleOwnerPetList.addAll(petOwner.pets!!)
            }
        }
        val femaleOwnerPetList = ArrayList<Pet>()
        for(petOwner:PetOwner in femalePetOwnersList!!) {
            if(petOwner.pets != null) {
                femaleOwnerPetList.addAll(petOwner.pets!!)
            }
        }

        val petSortedListMale = maleOwnerPetList.sortedWith(compareBy { it.name })
        val petSortedListFemale = femaleOwnerPetList.sortedWith(compareBy { it.name })

        val listToDisplay:ArrayList<String>? = ArrayList()
        for(pet: Pet in petSortedListMale) {
            if(pet.type == "Cat") {
                listToDisplay?.add("\u25CF " + pet.name)
            }
        }
        listToDisplay?.add("Female")
        for(pet: Pet in petSortedListFemale) {
            if(pet.type == "Cat") {
                listToDisplay?.add("\u25CF " + pet.name)
            }
        }
        listToDisplay?.add(0,"Male")
        return listToDisplay!!
    }
}