package com.sample.hiltdisampleapp.data.api

import com.sample.hiltdisampleapp.data.model.MoviePojo
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

}