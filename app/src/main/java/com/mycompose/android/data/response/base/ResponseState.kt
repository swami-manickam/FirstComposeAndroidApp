package com.mycompose.android.data.response.base

class AppResponse<T> private constructor(
    val status: ResponseStatus,
    val data: T?,
    val message: String?
) {
    enum class ResponseStatus {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T): AppResponse<T> {
            return AppResponse(ResponseStatus.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): AppResponse<T> {
            return AppResponse(ResponseStatus.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): AppResponse<T> {
            return AppResponse(ResponseStatus.LOADING, data, null)
        }
    }

}

