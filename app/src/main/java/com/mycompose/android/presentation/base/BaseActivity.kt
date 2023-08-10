package com.mycompose.android.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.mycompose.android.data.preferences.AppPreference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
open class BaseActivity : ComponentActivity() {


    @Inject
    lateinit var appPreference: AppPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appPreference = AppPreference(this)

    }

}