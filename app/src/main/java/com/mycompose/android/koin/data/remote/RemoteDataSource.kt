package com.mycompose.android.koin.data.remote

class RemoteDataSource(private val apiService: ApiService) {


    suspend fun getMovies() = apiService.getMovies()
    suspend fun getMovieDetail(movieId:Int) = apiService.getMovieDetail(movieId)

}