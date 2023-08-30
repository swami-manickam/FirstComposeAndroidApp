package com.mycompose.android.data.api

import com.mycompose.android.data.response.ProductDataResponse
import com.mycompose.android.data.response.UserDataResponse
import retrofit2.Response
import retrofit2.http.GET

interface ComposeApi {

    @GET(ApiConstants.GET_USERS)
    suspend fun getAllUserRecords(): Response<UserDataResponse>

    @GET(ApiConstants.GET_ALL_PRODUCTS)
    suspend fun getAllProduct() : Response<ProductDataResponse>



}