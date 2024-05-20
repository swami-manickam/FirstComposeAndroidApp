package com.mycompose.android.koin.data

import android.content.Context
import com.mycompose.android.koin.data.remote.RemoteDataSource
import com.mycompose.android.koin.data.remote.toResultFlow
import com.mycompose.android.utils.UiState
import kotlinx.coroutines.flow.Flow

class Repository(private val remoteDataSource: RemoteDataSource) {


    suspend fun getMovies(context: Context): Flow<UiState<MoviesRemoteModel>> {
        return toResultFlow(context) {
            remoteDataSource.getMovies()
        }
    }


    suspend fun getMovieDetail(context: Context, movieId: Int) : Flow<UiState<MovieResultRemoteModel>> {
        return toResultFlow(context){
            remoteDataSource.getMovieDetail(movieId)
        }

    }


}