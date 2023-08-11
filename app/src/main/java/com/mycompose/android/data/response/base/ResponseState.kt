package com.mycompose.android.data.response.base

import com.mycompose.android.data.response.base.ResponseStatus.ERROR
import com.mycompose.android.data.response.base.ResponseStatus.FAIL
import com.mycompose.android.data.response.base.ResponseStatus.SUCCESS

sealed class ResponseState<out R> {

    data class Loading<T>(val isLoading: T) : ResponseState<T>()

    data class Success<T>(val data: T) : ResponseState<T>()

    data class Failure<T>(val error: Throwable) : ResponseState<Throwable>()

}

class AppResponse<T> private constructor(
    val status: Int,
    val data: T?, val
    throwable: Throwable?
) {

    companion object {
        fun <T> success(data: T): AppResponse<T> {
            return AppResponse(SUCCESS, data, null)
        }

        fun <T> failure(data : T) : AppResponse<T>{
            return AppResponse(FAIL,data,null)
        }

        fun error(error: Throwable) : AppResponse<*>{
            return AppResponse(ERROR, null, error)
        }
    }

}

object ResponseStatus {
    const val ERROR = 0
    const val SUCCESS = 1
    const val FAIL = 2
}