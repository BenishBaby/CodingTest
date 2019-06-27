package com.ms.testcode.activity.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ms.testcode.R

class CatsNamesByOwnerGenderAdapter( private val listValues: ArrayList<String>?): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    override fun getItemViewType(position: Int): Int {
        return when (listValues?.get(position)) {
            "Male" , "Female"  -> PET_OWNER_GENDER_TYPE
            else -> PET_NAME_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            PET_OWNER_GENDER_TYPE -> {
                val catHolder = ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listview_heading, parent, false))
                catHolder
            }
            else -> {
                val catHolder = HeadingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.listviewcell, parent, false))
                catHolder
            }
        }
    }

    override fun getItemCount(): Int = listValues?.size!!

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (holder) {
            is ListViewHolder -> {
                holder.bind(listValues?.get(position) as String)
            }
            is HeadingViewHolder -> holder.bind(listValues?.get(position) as String)
            else -> throw IllegalArgumentException("Illegal view holder type supplied")
        }
    }

    companion object {
        const val PET_OWNER_GENDER_TYPE = 1
        const val PET_NAME_TYPE = 0
    }
}