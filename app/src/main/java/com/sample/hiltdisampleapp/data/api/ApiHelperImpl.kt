package com.sample.hiltdisampleapp.data.api


import com.sample.hiltdisampleapp.data.model.ForecastPojo
import com.sample.hiltdisampleapp.data.model.WeatherPojo
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getForecastDetails(city: String?, appid: String?): Response<ForecastPojo> =
        apiService.getForecastDetails(city, appid)

    override suspend fun getWeatherDetails(city: String?, appid: String?): Response<WeatherPojo> =
        apiService.getWeatherDetails(city, appid)


}