package com.sample.hiltdisampleapp.data.api


import com.sample.hiltdisampleapp.data.model.MoviePojo
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getPopularMovies(
        apiKey: String?,
        language: String?,
        page: Int
    ): Response<MoviePojo> {
        return apiService.getPopularMovies(apiKey, language, page)
    }

}