package com.mycompose.android.data.api

import com.mycompose.android.data.response.base.AppResponse
import retrofit2.Response
import java.io.IOException

abstract class BaseDataSource {


    protected suspend fun <T> getResult(call: suspend () -> Response<T>): AppResponse<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) return AppResponse.success(responseBody)
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: IOException) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): AppResponse<T> {
        return AppResponse.error("Network call has failed for a following reason: $message")
    }

}