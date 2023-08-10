package com.mycompose.android.data.repo

import com.mycompose.android.data.api.ComposeApi
import com.mycompose.android.data.local.ComposeDatabase
import javax.inject.Inject

class ComposeRepo @Inject constructor(private val composeApi: ComposeApi,private val composeDb: ComposeDatabase){
}