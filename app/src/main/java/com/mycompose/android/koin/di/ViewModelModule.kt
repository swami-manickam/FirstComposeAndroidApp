package com.mycompose.android.koin.di

import com.mycompose.android.presentation.product.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ProductViewModel(get())
    }
}