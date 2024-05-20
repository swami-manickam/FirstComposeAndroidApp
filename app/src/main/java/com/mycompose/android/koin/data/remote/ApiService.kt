package com.mycompose.android.koin.data.remote

import com.mycompose.android.koin.ApiConstants.GET_MOVIES
import com.mycompose.android.koin.ApiConstants.GET_MOVIE_DETAIL
import com.mycompose.android.koin.data.MovieResultRemoteModel
import com.mycompose.android.koin.data.MoviesRemoteModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(GET_MOVIES)
    suspend fun getMovies(): Response<MoviesRemoteModel>

    @GET(GET_MOVIE_DETAIL)
    suspend fun getMovieDetail(movieId: Int): Response<MovieResultRemoteModel>

}