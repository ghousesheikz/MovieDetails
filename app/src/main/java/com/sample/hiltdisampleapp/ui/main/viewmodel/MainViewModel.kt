package com.sample.hiltdisampleapp.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.sample.hiltdisampleapp.data.model.ForecastPojo
import com.sample.hiltdisampleapp.data.model.WeatherPojo
import com.sample.hiltdisampleapp.data.repository.MainRepository
import com.sample.hiltdisampleapp.utils.Constants.APPID
import com.sample.hiltdisampleapp.utils.Constants.CITY
import com.sample.hiltdisampleapp.utils.NetworkHelper
import com.sample.hiltdisampleapp.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _weatherData = MutableLiveData<Resource<WeatherPojo>>()
    private val _forecastData = MutableLiveData<Resource<ForecastPojo>>()
    val forecastData: LiveData<Resource<ForecastPojo>>
        get() = _forecastData
    val weatherData: LiveData<Resource<WeatherPojo>>
        get() = _weatherData

    init {
        fetchForecastData(CITY, APPID)
        fetchWeatherData(CITY, APPID)
    }

    private fun fetchWeatherData(city: String, appid: String) {
        viewModelScope.launch {
            _weatherData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getWeatherDetails(city, appid).let {
                    if (it.isSuccessful) {
                        _weatherData.postValue(Resource.success(it.body()))
                    } else _weatherData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _weatherData.postValue(Resource.error("No internet connection", null))
        }
    }

    private fun fetchForecastData(city:String,appid:String) {
        viewModelScope.launch {
            _forecastData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getForecastDetails(city, appid).let {
                    if (it.isSuccessful) {
                        _forecastData.postValue(Resource.success(it.body()))
                    } else _forecastData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _forecastData.postValue(Resource.error("No internet connection", null))
        }
    }


}