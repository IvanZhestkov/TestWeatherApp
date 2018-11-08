package com.itis.android.myweatherapp.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import com.itis.android.myweatherapp.model.City
import com.itis.android.myweatherapp.utils.loadPicture
import kotlinx.android.synthetic.main.item_city.view.*

class MainViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: City) = with(itemView) {
        tv_name_city.text = item.name
        tv_temp_city.text = item.temp + "\u00B0" + "C"
        loadPicture(iv_cover_city, item.image)
    }
}