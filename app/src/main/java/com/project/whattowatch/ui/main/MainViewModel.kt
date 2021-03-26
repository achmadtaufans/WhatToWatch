package com.project.whattowatch.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.whattowatch.common.data.MovieModel
import com.project.whattowatch.common.repository.MovieRepository
import com.project.whattowatch.common.util.Utils

class MainViewModel(private val movieRepository: MovieRepository): ViewModel() {
    val actionType: MutableLiveData<Int> = MutableLiveData<Int>().apply { 0 }
    val categoryType: MutableLiveData<String> = MutableLiveData<String>().apply { "" }
    val movieList: ArrayList<MovieModel> = ArrayList()
    val categoryTitle: MutableLiveData<String> = MutableLiveData<String>().apply { "" }
    val categorySubtitle: MutableLiveData<String> = MutableLiveData<String>().apply { "" }

    companion object {
        const val UPDATE_ADAPTER = 1
        const val OPEN_DIALOG_CATEGORY = 2

        const val POPULAR = "Popular"
        const val UPCOMING = "Upcoming"
        const val TOP_RATED = "Top Rated"
        const val NOW_PLAYING = "Now Playing"
        const val FAVORITE = "Favorite"
    }

    fun loadData() {
        getPopularMovies()
    }

    fun setAdapterByCategory(category: String) {
        when (category) {
            POPULAR -> getPopularMovies()
            UPCOMING -> getUpcomingMovies()
            TOP_RATED -> getTopRatedMovies()
            NOW_PLAYING -> getNowPlayingMovies()
        }
    }

    private fun getPopularMovies() {
        movieList.clear()

        categoryType.value = POPULAR

        movieRepository.getPopularMovies(
            onSuccess = { movies ->
                movieList.addAll(movies)
                actionType.postValue(UPDATE_ADAPTER)
            },
            onError = {}
        )
    }

    private fun getUpcomingMovies() {
        movieList.clear()

        categoryType.value = UPCOMING

        movieRepository.getUpcomingMovies(
            onSuccess = { movies ->
                movieList.addAll(movies)
                actionType.postValue(UPDATE_ADAPTER)
            },
            onError = {}
        )
    }

    private fun getTopRatedMovies() {
        movieList.clear()

        categoryType.value = TOP_RATED

        movieRepository.getTopRatedMovies(
            onSuccess = { movies ->
                movieList.addAll(movies)
                actionType.postValue(UPDATE_ADAPTER)
            },
            onError = {}
        )
    }

    private fun getNowPlayingMovies() {
        movieList.clear()

        categoryType.value = POPULAR

        movieRepository.getNowPlayingMovies(
            onSuccess = { movies ->
                movieList.addAll(movies)
                actionType.postValue(UPDATE_ADAPTER)
            },
            onError = {}
        )
    }

    fun openDialogCategory() {
        actionType.value = OPEN_DIALOG_CATEGORY
    }

    fun openFavoriteMovies() {
        movieList.clear()

        categoryType.value = FAVORITE

        Utils.getFavoriteMovies()?.forEach {
            movieList.add(it)
        }

        actionType.postValue(UPDATE_ADAPTER)
    }
}