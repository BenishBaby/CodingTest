package com.ms.testcode.activity.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.listviewcell.view.*

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val petNameTextView: TextView = itemView.list_cell_value
    fun bind(value : String) {
        petNameTextView.text = value
    }
}