package com.sample.hiltdisampleapp.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.hiltdisampleapp.data.model.MoviePojo
import com.sample.hiltdisampleapp.data.repository.MainRepository
import com.sample.hiltdisampleapp.utils.Constants.API_KEY
import com.sample.hiltdisampleapp.utils.Constants.LANGUAGE
import com.sample.hiltdisampleapp.utils.Constants.PAGE
import com.sample.hiltdisampleapp.utils.NetworkHelper
import com.sample.hiltdisampleapp.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _popularMovieData = MutableLiveData<Resource<MoviePojo>>()

    val popularMovieData: LiveData<Resource<MoviePojo>>
        get() = _popularMovieData

    init {
        fetchPopularMovies(API_KEY, LANGUAGE, PAGE)
    }


    private fun fetchPopularMovies(apiKey: String?, language: String?, page: Int) {
        viewModelScope.launch {
            _popularMovieData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getPopularMovies(apiKey, language, page).let {
                    if (it.isSuccessful) {
                        _popularMovieData.postValue(Resource.success(it.body()))
                    } else _popularMovieData.postValue(
                        Resource.error(
                            it.errorBody().toString(),
                            null
                        )
                    )
                }
            } else _popularMovieData.postValue(Resource.error("No internet connection", null))
        }
    }

}