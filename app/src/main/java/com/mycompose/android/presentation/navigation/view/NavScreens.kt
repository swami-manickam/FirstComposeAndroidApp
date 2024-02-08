package com.mycompose.android.presentation.navigation.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.mycompose.app.R

sealed class NavScreens(val route: String, val title: String) {

    sealed class HomeScreens(
        route: String,
        title: String,
        val icon: ImageVector
    ) : NavScreens(route, title) {
        object Favorite : HomeScreens("favorite", "Favorite", Icons.Filled.Favorite)
        object NearBy : HomeScreens("nearby", "Nearby", Icons.Filled.Notifications)
        object Reserved : HomeScreens("reserved", "Reserved", Icons.Filled.Person)
        object Saved : HomeScreens("saved", "Saved", Icons.Filled.Person)

    }


    sealed class DrawerScreens(route: String, val icon: Int,  title: String) : NavScreens(route, title) {

        object Home : DrawerScreens("home", R.drawable.ic_faq_new, "Home")
        object MyProfile : DrawerScreens("myprofile", R.drawable.ic_faq_new, "MyProfile")
        object Settings : DrawerScreens("settings", R.drawable.ic_faq_new, "Settings")
        object Help : DrawerScreens("help", R.drawable.ic_faq_new, "Help")
        object AboutUs : DrawerScreens("aboutus", R.drawable.ic_faq_new, "AboutUs")
        object Logout : DrawerScreens("logout", R.drawable.ic_faq_new, "Logout")
    }
}

val screensInHomeFromBottomNav = listOf(
    NavScreens.HomeScreens.Favorite,
    NavScreens.HomeScreens.NearBy,
    NavScreens.HomeScreens.Reserved,
    NavScreens.HomeScreens.Saved
)


val screensFromDrawer = listOf(
    NavScreens.DrawerScreens.Home,
    NavScreens.DrawerScreens.MyProfile,
    NavScreens.DrawerScreens.Settings,
    NavScreens.DrawerScreens.Help,
    NavScreens.DrawerScreens.AboutUs,
    NavScreens.DrawerScreens.Logout
)