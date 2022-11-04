package com.sample.hiltdisampleapp.data.repository

import com.sample.hiltdisampleapp.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {


    suspend fun getPopularMovies(apiKey: String?, language: String?, page: Int) =
        apiHelper.getPopularMovies(apiKey, language, page)

    suspend fun getForecastDetails(city: String?, appid: String?) =
        apiHelper.getForecastDetails(city, appid)

    suspend fun getWeatherDetails(city: String?, appid: String?) =
        apiHelper.getWeatherDetails(city, appid)
}