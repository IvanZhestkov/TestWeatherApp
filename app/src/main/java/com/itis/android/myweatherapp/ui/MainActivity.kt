package com.itis.android.myweatherapp.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.itis.android.myweatherapp.R
import com.itis.android.myweatherapp.model.City
import com.itis.android.myweatherapp.model.Main
import com.itis.android.myweatherapp.mvp.MainPresenter
import com.itis.android.myweatherapp.mvp.MainView
import com.itis.android.myweatherapp.utils.fromKtoC
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var images: Array<String>

    private lateinit var citiesNames: Array<String>

    private var adapter: MainAdapter? = null

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Weather"
        setSupportActionBar(toolbar)
        setUpRecyclerView()
        setUpSwipeToRefreshLayout()
    }

    override fun showItems(list: ArrayList<Main?>) {
        val cities: ArrayList<City> = ArrayList()
        for (i in 0 until list.size) {
            cities.add(City(citiesNames[i], fromKtoC(list[i]?.temp), images[i]))
        }
        adapter?.setItems(cities)
    }

    override fun handleError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    override fun addMoreItems(items: List<City>) {
        adapter?.addItems(items)
    }

    override fun showRefreshing() {
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun hideRefreshing() {
        swipe_refresh.isRefreshing = false
    }

    private fun setUpRecyclerView() {
        images = resources.getStringArray(R.array.cities_images)
        citiesNames = resources.getStringArray(R.array.cities_names)

        adapter = MainAdapter()
        rv_cities_list.layoutManager = LinearLayoutManager(this)
        rv_cities_list.adapter = adapter
    }

    private fun setUpSwipeToRefreshLayout() {
        swipe_refresh.setOnRefreshListener { presenter.loadRefresh() }
        swipe_refresh.setColorSchemeResources(R.color.colorAccent)
    }
}
