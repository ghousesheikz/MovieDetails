package com.sample.hiltdisampleapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.hiltdisampleapp.R
import com.sample.hiltdisampleapp.data.model.MoviePojo
import com.sample.hiltdisampleapp.utils.Constants
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(private val movieList: ArrayList<MoviePojo.Movies>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movieDetails: MoviePojo.Movies) {
            itemView.apply {
                txtTitle.text = movieDetails.title
                Glide.with(this).load(Constants.IMAGE_BASE_URL.plus(movieDetails.poster_path))
                    .into(image)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun addMovieData(movieDetails: List<MoviePojo.Movies>) {
        this.movieList.apply {
            clear()
            addAll(movieDetails)
        }
    }
}