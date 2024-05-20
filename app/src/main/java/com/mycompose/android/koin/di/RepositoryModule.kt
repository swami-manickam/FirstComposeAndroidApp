package com.mycompose.android.koin.di

import com.mycompose.android.koin.data.Repository
import org.koin.dsl.module


val repositoryModule = module {
    factory { Repository(get()) }
}