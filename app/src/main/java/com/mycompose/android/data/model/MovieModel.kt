package com.mycompose.android.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MovieModel (
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val realName: Int,
    @StringRes val superpowers : Int
)