package com.sample.hiltdisampleapp.data.api

import com.sample.hiltdisampleapp.data.model.ForecastPojo
import com.sample.hiltdisampleapp.data.model.WeatherPojo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("forecast")
    suspend fun getForecastDetails(
        @Query(encoded = true, value = "q") q: String?,
        @Query(encoded = true, value = "APPID") app_id: String?
    ): Response<ForecastPojo>

    @GET("weather")
    suspend fun getWeatherDetails(
        @Query(encoded = true, value = "q") q: String?,
        @Query(encoded = true, value = "APPID") app_id: String?
    ): Response<WeatherPojo>
}