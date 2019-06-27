package com.ms.testcode.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View.GONE
import android.widget.Toast
import com.ms.testcode.R
import com.ms.testcode.activity.adapter.CatsNamesByOwnerGenderAdapter
import kotlinx.android.synthetic.main.activity_main.*


class CatsNamesByOwnerGenderActivity : AppCompatActivity(), CatNamesByOwnerGenderView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val presenter = CatNamesByOwnerGenderPresenterImpl(this)
        presenter.getPetOwnerDetails()
    }


    override fun showAlertOnServiceUnavailability() {
        Toast.makeText(this@CatsNamesByOwnerGenderActivity, R.string.service_unavailable_error_message, Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        progress_bar.visibility  =  GONE
    }

    override fun showPetNamesListByOwnerGender(petsAndOwnderGenderList: ArrayList<String>) {

        val catsNamesByOwnerGenderAdapter = CatsNamesByOwnerGenderAdapter(petsAndOwnderGenderList)
        val linearLayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.adapter = catsNamesByOwnerGenderAdapter
    }
}
