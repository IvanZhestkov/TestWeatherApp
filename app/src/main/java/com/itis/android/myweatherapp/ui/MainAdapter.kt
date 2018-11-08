package com.itis.android.myweatherapp.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.itis.android.myweatherapp.R
import com.itis.android.myweatherapp.model.City

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private val cities: ArrayList<City> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewTipe: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    private fun getItem(position: Int): City {
        return cities[position]
    }

    fun addItems(newItems: List<City>) {
        cities.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItem(city: City) {
        cities.add(city)
        notifyDataSetChanged()
    }

    fun setItems(items: List<City>) {
        clearList()
        addItems(items)
    }

    private fun clearList() {
        cities.clear()
    }
}