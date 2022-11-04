package com.sample.hiltdisampleapp.data.api

import com.sample.hiltdisampleapp.data.model.ForecastPojo
import com.sample.hiltdisampleapp.data.model.MoviePojo
import com.sample.hiltdisampleapp.data.model.WeatherPojo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query(encoded = true, value = "api_key") api_key: String?,
        @Query(encoded = true, value = "language") language: String?,
        @Query(encoded = true, value = "page") page: Int?
    ): Response<MoviePojo>

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