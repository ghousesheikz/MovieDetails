package com.sample.hiltdisampleapp.data.api

import com.sample.hiltdisampleapp.data.model.ForecastPojo
import com.sample.hiltdisampleapp.data.model.MoviePojo
import com.sample.hiltdisampleapp.data.model.WeatherPojo
import retrofit2.Response

interface ApiHelper {

    suspend fun getPopularMovies(apiKey: String?, language: String?, page: Int): Response<MoviePojo>

    suspend fun getForecastDetails(city: String?, appid: String?): Response<ForecastPojo>

    suspend fun getWeatherDetails(city: String?, appid: String?): Response<WeatherPojo>

}