package com.mycompose.android.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.mycompose.app.R

sealed class BottomNavItem(val route: String, val icon: Int, val title: String) {

    object Home : BottomNavItem("home", R.drawable.ic_faq_new, "Home")
    object Notification : BottomNavItem("notification", R.drawable.ic_faq_new, "Notification")
    object Profile : BottomNavItem("profile", R.drawable.ic_faq_new, "Profile")

}