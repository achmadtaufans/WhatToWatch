package com.project.whattowatch.common.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.google.gson.Gson
import com.project.whattowatch.WhatToWatch
import com.project.whattowatch.common.data.MovieModel

object Utils {
    const val API_KEY = "e09c4a3b47b1bd578b025b8f9101c12b"
    const val SP_KEY = "WhatToWatch"
    const val FAV_MOVIE_KEY = "favorite_movies"

    lateinit var sharedPreferences: SharedPreferences
    var gson: Gson = Gson()

    fun showImage(path: String, itemView: View, imageView: ImageView) {
        Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${path}")
                .transform(CenterCrop())
                .into(imageView)
    }

    fun setFavoriteMovies(carConditionData: List<MovieModel>?) {
        Log.e("coba", carConditionData.toString())
        val json: String = gson.toJson(carConditionData)
        Log.e("coba2", json.toString())
        sharedPreferences = WhatToWatch.applicationContext().getSharedPreferences(SP_KEY, Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()

        editor.putString(FAV_MOVIE_KEY, json)
        editor.apply()
    }

    fun getFavoriteMovies(context: Context = WhatToWatch.applicationContext()): Collection<MovieModel>? {
        var sharedPreferences = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE)

        val json = sharedPreferences.getString(FAV_MOVIE_KEY, null)
        var movieList: List<MovieModel>? = null

        if (json != null) {
            movieList = gson.fromJson(json, Array<MovieModel>::class.java).toList()
        }

        return movieList
    }

    fun removeFavoriteMovies(id: Long) {
        sharedPreferences = WhatToWatch.applicationContext().getSharedPreferences(SP_KEY, Context.MODE_PRIVATE)

        val json = sharedPreferences.getString(FAV_MOVIE_KEY, null)
        var movieList: MutableList<MovieModel>? = null

        if (json != null) {
            movieList = gson.fromJson(json, Array<MovieModel>::class.java).toMutableList()
            getFavoriteMovies()?.forEach {
                if (it.id == id) {
                    movieList.remove(it)
                    setFavoriteMovies(movieList)
                }
            }
        }
    }
}