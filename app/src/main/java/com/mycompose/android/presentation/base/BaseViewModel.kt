package com.mycompose.android.presentation.base

import androidx.lifecycle.ViewModel
import com.mycompose.android.data.preferences.AppPreference
import com.mycompose.android.data.repo.ComposeRepo
import javax.inject.Inject

open class BaseViewModel :ViewModel(){

    @Inject
    lateinit var composeRepo: ComposeRepo

    @Inject
    lateinit var appPreference : AppPreference

}