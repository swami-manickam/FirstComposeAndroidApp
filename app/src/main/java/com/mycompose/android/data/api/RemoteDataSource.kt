package com.mycompose.android.data.api

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val appApi: ComposeApi
) : BaseDataSource() {


    suspend fun getProducts() = getResult {
        appApi.getAllProduct() }


   suspend fun getProductsFlow() = getResult {
        appApi.getAllProduct()
    }


}