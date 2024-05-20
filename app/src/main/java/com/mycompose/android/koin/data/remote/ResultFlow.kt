package com.mycompose.android.koin.data.remote

import android.content.Context
import com.mycompose.android.utils.UiState
import com.mycompose.android.utils.UiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response


inline fun <reified ,T> toResultFlow(context: Context,
                                     crossinline call : suspend () -> Response<T>) : Flow<UiState<T>>{
    return flow {
        val isConnected = UiUtils.hasInternetConnection(context)

        if(isConnected) {
            emit(UiState.Loading)
            try {
                val response = call()
                if(response.isSuccessful && response.body()!=null)
                    emit(UiState.Success(response.body()))
                else
                    emit(UiState.Error(response.message()))

            }catch (e : Exception){
                emit(UiState.Error(e.toString()))
            }
        }else {
            emit(UiState.Error(""))
        }

    }.flowOn(Dispatchers.IO)

}