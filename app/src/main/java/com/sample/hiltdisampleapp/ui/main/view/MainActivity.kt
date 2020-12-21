package com.sample.hiltdisampleapp.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.hiltdisampleapp.R
import com.sample.hiltdisampleapp.data.model.ForecastPojo
import com.sample.hiltdisampleapp.ui.main.adapter.MainAdapter
import com.sample.hiltdisampleapp.ui.main.viewmodel.MainViewModel
import com.sample.hiltdisampleapp.utils.Constants
import com.sample.hiltdisampleapp.utils.Constants.startAnimation
import com.sample.hiltdisampleapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel : MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startAnimation(progress_view)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {


        mainViewModel.weatherData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    weather_cardView.visibility = View.VISIBLE
                    progress_view.visibility = View.GONE

                    it.data?.let { weatherData ->
                        txt_temperature.text =
                            Constants.calculateTemperature(weatherData.main.temp)
                        txt_city.text = weatherData.name
                        Log.i("APPDATA", weatherData.cod.toString())
                    }
                }
                Status.LOADING -> {
                    progress_view.visibility = View.VISIBLE
                    weather_cardView.visibility = View.GONE
                }
                Status.ERROR -> {
                    weather_cardView.visibility = View.VISIBLE
                    progress_view.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        mainViewModel.forecastData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    forecast_card.visibility = View.VISIBLE
                    progress_view.visibility = View.GONE
                    it.data?.let { weatherForeCast ->
                        retrieveList(weatherForeCast.list)
                        Log.i("APPDATA", weatherForeCast.cod.toString())
                    }
                }
                Status.LOADING -> {
                    progress_view.visibility = View.VISIBLE
                    forecast_card.visibility = View.GONE
                }
                Status.ERROR -> {
                    forecast_card.visibility = View.VISIBLE
                    progress_view.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun retrieveList(forecastData: List<ForecastPojo.WeatherList>) {
        adapter.apply {
            addForecast(forecastData)
            notifyDataSetChanged()
        }
    }
}