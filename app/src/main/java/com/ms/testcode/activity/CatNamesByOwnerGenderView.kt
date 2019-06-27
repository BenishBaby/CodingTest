package com.ms.testcode.activity

interface CatNamesByOwnerGenderView {

    fun showPetNamesListByOwnerGender(petsAndOwnderGenderList: ArrayList<String>)
    fun showAlertOnServiceUnavailability()
    fun hideLoading()
}