package com.project.whattowatch.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.project.whattowatch.R
import com.project.whattowatch.common.data.MovieModel
import com.project.whattowatch.common.util.Utils

class MovieAdapter (private var itemList: MutableList<MovieModel>, internal var ctx: Context) : RecyclerView.Adapter<MovieAdapter.Holder>() {
    private lateinit var itemMovieListCallback: ItemMovieClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)
        val title: TextView = itemView.findViewById(R.id.title)
        val releaseDate: TextView = itemView.findViewById(R.id.release_date)
        val description: TextView = itemView.findViewById(R.id.description)
        val movie_item: CardView = itemView.findViewById(R.id.cv_movie_item)

        @SuppressLint("SetTextI18n")
        fun bind(movie: MovieModel) {
            title.text = movie.title
            releaseDate.text = movie.releaseDate
            description.text = if (movie.overview.length > 300) {
                movie.overview.substring(0, 300).substringBeforeLast(" ") + " ..."
            } else {
                movie.overview
            }

            Utils.showImage(movie.posterPath, itemView, poster)
        }

        init {
            movie_item.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            when(view.id) {
                R.id.cv_movie_item -> {
                    itemMovieListCallback.onItemClick(adapterPosition)
                }
            }
        }
    }

    interface ItemMovieClickCallback {
        fun onItemClick(p: Int)
    }

    fun setItemClickCallback(itemMovieClickCallback: ItemMovieClickCallback) {
        this.itemMovieListCallback = itemMovieClickCallback
    }
}