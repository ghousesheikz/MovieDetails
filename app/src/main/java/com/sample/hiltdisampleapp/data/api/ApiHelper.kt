package com.sample.hiltdisampleapp.data.api

import com.sample.hiltdisampleapp.data.model.MoviePojo
import retrofit2.Response

interface ApiHelper {

    suspend fun getPopularMovies(apiKey: String?, language: String?, page: Int): Response<MoviePojo>

}