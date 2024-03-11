package com.mycompose.android.data.repo

import com.mycompose.android.data.api.RemoteDataSource
import com.mycompose.android.data.api.performFlowOperation
import com.mycompose.android.data.api.performOperation
import com.mycompose.android.data.local.dao.ComposeDAO
import com.mycompose.android.data.response.ProductDataResponse
import com.mycompose.android.data.response.base.AppResponse
import retrofit2.Response
import javax.inject.Inject

class ComposeRepo @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val composeDao: ComposeDAO
) {

/*Methode 1*/
    fun getAllProducts() = performOperation(
        networkCall = { remoteDataSource.getProducts() }
    )
    /*Methode 2*/
    suspend fun getAllProductsList() : AppResponse<ProductDataResponse> {
        return remoteDataSource.getProducts()
    }

    /*Method 3 */

    suspend fun getAllProductsListFlow() = performFlowOperation (
        networkCall = { remoteDataSource.getProductsFlow() }
    )



}