package com.project.whattowatch.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.whattowatch.common.data.MovieModel
import com.project.whattowatch.common.data.ReviewsModel
import com.project.whattowatch.common.repository.MovieRepository
import com.project.whattowatch.common.util.Utils
import kotlinx.coroutines.launch

class DetailViewModel(private val movieRepository: MovieRepository): ViewModel() {
    val actionType: MutableLiveData<Int> = MutableLiveData<Int>().apply { 0 }
    val movieId: MutableLiveData<Long> = MutableLiveData<Long>().apply { 0L }
    val titleMovie: MutableLiveData<String> = MutableLiveData<String>().apply { "" }
    val releaseDateMovie: MutableLiveData<String> = MutableLiveData<String>().apply { "" }
    val descriptionMovie: MutableLiveData<String> = MutableLiveData<String>().apply { "" }
    val imagePath: MutableLiveData<String> = MutableLiveData<String>().apply { "" }
    val rateMovie: MutableLiveData<String> = MutableLiveData<String>().apply { "" }
    val reviewerList: ArrayList<ReviewsModel> = ArrayList()
    val myFavoriteMoviesList: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { false }
    val favoriteList: ArrayList<MovieModel> = ArrayList()

    companion object {
        const val UPDATE_ADAPTER = 1
    }

    fun loadData() {
        getMovieDetail()
        getMovieReviewsDetail()
        checkFavoriteList()
    }

    private fun getMovieDetail() {
        favoriteList.clear()
        viewModelScope.launch {
            movieRepository.getDetailMovies(
                movieId.value,
                onSuccess = { movies ->
                    favoriteList.add(movies)
                    titleMovie.postValue(movies.title)
                    releaseDateMovie.postValue(movies.releaseDate)
                    descriptionMovie.postValue(movies.overview)
                    imagePath.postValue(movies.posterPath)
                    rateMovie.postValue(movies.rating.toString())
                },
                onError = {}
            )
        }
    }

    private fun getMovieReviewsDetail() {
        reviewerList.clear()
        viewModelScope.launch {
            movieRepository.getDetailReviewsMovie(
                    movieId.value,
                    onSuccess = { reviewer ->
                        reviewerList.addAll(reviewer)
                        actionType.postValue(UPDATE_ADAPTER)
                    },
                    onError = {}
            )
        }
    }

    private fun checkFavoriteList() {
        Utils.getFavoriteMovies()?.forEach {
            if (it.id == movieId.value) {
                myFavoriteMoviesList.postValue(true)
            }
        }
    }

    fun onClickIconFavorite() {
        if (myFavoriteMoviesList.value == true) {
            Utils.removeFavoriteMovies(movieId.value ?: 0)
            myFavoriteMoviesList.postValue(false)
        } else {
            Utils.getFavoriteMovies()?.forEach {
                favoriteList.add(it)
            }
            Utils.setFavoriteMovies(favoriteList)

            myFavoriteMoviesList.postValue(true)
        }
    }
}