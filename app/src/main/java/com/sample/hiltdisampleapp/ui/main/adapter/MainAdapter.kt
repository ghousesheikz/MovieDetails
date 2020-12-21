package com.sample.hiltdisampleapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.hiltdisampleapp.R
import com.sample.hiltdisampleapp.data.model.ForecastPojo
import com.sample.hiltdisampleapp.utils.Constants.calculateTemperature
import com.sample.hiltdisampleapp.utils.Constants.retriveWeekDays
import com.sample.hiltdisampleapp.utils.SimpleAnimator
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlin.collections.ArrayList

class MainAdapter(private val weatherForeCastList: ArrayList<ForecastPojo.WeatherList>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    private lateinit var simpleItemAnimator: SimpleAnimator

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(weatherForeCast: ForecastPojo.WeatherList) {
            itemView.apply {
                txt_dayname.text=retriveWeekDays(weatherForeCast.dt.toLong())
                txt_temperature.text = calculateTemperature(weatherForeCast.main.temp)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    override fun getItemCount(): Int = weatherForeCastList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        simpleItemAnimator = SimpleAnimator()
        simpleItemAnimator.animateAdd(holder)
        holder.bind(weatherForeCastList[position])
    }

    fun addForecast(weatherForeCast: List<ForecastPojo.WeatherList>) {
        this.weatherForeCastList.apply {
            clear()
            addAll(weatherForeCast)
        }
    }
}