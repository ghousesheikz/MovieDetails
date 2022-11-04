package com.sample.hiltdisampleapp.ui.main.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.sample.hiltdisampleapp.R
import com.sample.hiltdisampleapp.data.model.MoviePojo
import com.sample.hiltdisampleapp.ui.main.adapter.MainAdapter
import com.sample.hiltdisampleapp.ui.main.viewmodel.MainViewModel
import com.sample.hiltdisampleapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = MainAdapter(arrayListOf())
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {

        mainViewModel.popularMovieData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.v("MOVIE_DETAILS", Gson().toJson(it.data))
                    it.data.let { movie ->
                        movie?.results?.let { it1 -> retrieveMovieList(it1) }
                    }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

    }

    private fun retrieveMovieList(movieDetails: List<MoviePojo.Movies>) {
        adapter.apply {
            adapter.addMovieData(movieDetails)
            notifyDataSetChanged()
        }
    }
}