package com.mycompose.android.data.repo

import com.mycompose.android.data.api.RemoteDataSource
import com.mycompose.android.data.api.performOperation
import com.mycompose.android.data.local.dao.ComposeDAO
import javax.inject.Inject

class ComposeRepo @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val composeDao: ComposeDAO
) {


    fun getAllProducts() = performOperation(
        networkCall = { remoteDataSource.getProducts() }
    )


}