package com.mycompose.android.data.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreference @Inject constructor(context: Context) {

    companion object {
        private const val PREFERENCE_NAME = "COMPOSE_PREF"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun clearPreferences() {
        preferences.edit().clear().apply()
    }


}