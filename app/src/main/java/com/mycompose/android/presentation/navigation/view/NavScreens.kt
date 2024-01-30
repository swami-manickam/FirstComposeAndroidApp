package com.mycompose.android.presentation.navigation.view

import com.mycompose.app.R

sealed class NavScreens(val route: String, val title: String) {

    sealed class DrawerScreens(val route: String, val icon: Int, val title: String) {
        object Home : DrawerScreens("home", R.drawable.ic_faq_new, "Home")
        object Settings : DrawerScreens("settings", R.drawable.ic_faq_new, "Settings")
        object Help : DrawerScreens("help", R.drawable.ic_faq_new, "Help")
        object AboutUs : DrawerScreens("aboutus", R.drawable.ic_faq_new, "AboutUs")
        object Logout : DrawerScreens("logout", R.drawable.ic_faq_new, "Logout")
    }
}


val screensFromDrawer = listOf(
    NavScreens.DrawerScreens.Home,
    NavScreens.DrawerScreens.Settings,
    NavScreens.DrawerScreens.Help,
    NavScreens.DrawerScreens.AboutUs,
    NavScreens.DrawerScreens.Logout
)