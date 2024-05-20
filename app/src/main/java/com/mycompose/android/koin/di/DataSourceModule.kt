package com.mycompose.android.koin.di

import com.mycompose.android.koin.data.remote.RemoteDataSource
import org.koin.dsl.module


val remoteDataSourceModule = module {
    factory {
        RemoteDataSource(get())
    }
}