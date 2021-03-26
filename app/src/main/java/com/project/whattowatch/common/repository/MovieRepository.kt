package com.project.whattowatch.common.repository

import com.project.whattowatch.common.data.*
import com.project.whattowatch.common.util.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = Utils.API_KEY,
    ): Call<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = Utils.API_KEY,
    ): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = Utils.API_KEY,
    ): Call<MoviesResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = Utils.API_KEY,
    ): Call<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovies(
        @Path("movie_id", encoded = true) movieId: Long,
        @Query("api_key") apiKey: String = Utils.API_KEY
    ): Call<MovieModel>

    @GET("movie/{movie_id}" + "/reviews")
    fun getDetailReviewsMovie(
        @Path("movie_id", encoded = true) movieId: Long,
        @Query("api_key") apiKey: String = Utils.API_KEY
    ): Call<ReviewsResponse>
}

class MovieRepository(private val movieApi: MovieService) {
    fun getPopularMovies(
        onSuccess: (movies: List<MovieModel>) -> Unit,
        onError: () -> Unit
    ) {
        movieApi.getPopularMovies()
            .enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getUpcomingMovies(
        onSuccess: (movies: List<MovieModel>) -> Unit,
        onError: () -> Unit
    ) {
        movieApi.getUpcomingMovies()
            .enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getTopRatedMovies(
        onSuccess: (movies: List<MovieModel>) -> Unit,
        onError: () -> Unit
    ) {
        movieApi.getTopRatedMovies()
            .enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getNowPlayingMovies(
        onSuccess: (movies: List<MovieModel>) -> Unit,
        onError: () -> Unit
    ) {
        movieApi.getNowPlayingMovies()
            .enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }

    fun getDetailMovies(
            movieId: Long? = 0L,
            onSuccess: (movies: MovieModel) -> Unit,
            onError: () -> Unit
    ) {
        movieApi.getDetailMovies(movieId = movieId ?: 0L)
                .enqueue(object : Callback<MovieModel> {
                    override fun onResponse(
                            call: Call<MovieModel>,
                            response: Response<MovieModel>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                onSuccess.invoke(responseBody)
                            } else {
                                onError.invoke()
                            }
                        }
                    }

                    override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                        onError.invoke()
                    }
                })
    }

    fun getDetailReviewsMovie(
        movieId: Long? = 0L,
        onSuccess: (reviewer: List<ReviewsModel>) -> Unit,
        onError: () -> Unit
    ) {
        movieApi.getDetailReviewsMovie(movieId = movieId ?: 0L)
            .enqueue(object : Callback<ReviewsResponse> {
                override fun onResponse(
                    call: Call<ReviewsResponse>,
                    response: Response<ReviewsResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.movies)
                        } else {
                            onError.invoke()
                        }
                    }
                }

                override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}