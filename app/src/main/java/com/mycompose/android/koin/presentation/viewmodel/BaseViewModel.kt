package com.mycompose.android.koin.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
protected val context
    get() = getApplication<Application>()

}