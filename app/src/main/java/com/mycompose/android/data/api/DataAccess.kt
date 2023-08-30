package com.mycompose.android.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.mycompose.android.data.response.base.AppResponse

import kotlinx.coroutines.Dispatchers


fun <T> performOperation(
   /* databaseQuery: () -> LiveData<T>,*/
    networkCall: suspend () -> AppResponse<T>,
    /*saveCallResult: suspend (A) -> Unit*/
): LiveData<AppResponse<T>> =

    liveData(Dispatchers.IO) {
        emit(AppResponse.loading())

        //
       /* val source = databaseQuery.invoke().map { AppResponse.success(it) }
        emit(source)*/
        //
        val response = networkCall.invoke()
        val data = MutableLiveData<AppResponse<T>>()
        data.postValue(response)
        if (response.status == AppResponse.ResponseStatus.SUCCESS) {
            //
            emitSource(data)
            /*saveCallResult(response.data!!)*/
        } else if (response.status == AppResponse.ResponseStatus.ERROR) {
            //
            emit(AppResponse.error(response.message!!))
            emitSource(data)
        }
    }


