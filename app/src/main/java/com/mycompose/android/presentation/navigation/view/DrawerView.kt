package com.mycompose.android.presentation.navigation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mycompose.android.presentation.navigation.DrawerAboutUsScreen
import com.mycompose.android.presentation.navigation.DrawerHelpScreen
import com.mycompose.android.presentation.navigation.DrawerHomeScreen
import com.mycompose.android.presentation.navigation.DrawerLogoutScreen
import com.mycompose.android.presentation.navigation.DrawerMyProfileScreen
import com.mycompose.android.presentation.navigation.DrawerSettingsScreen
import com.mycompose.android.presentation.navigation.NavFavoriteScreen
import com.mycompose.android.presentation.navigation.NavNearbyScreen
import com.mycompose.android.presentation.navigation.NavReservedScreen
import com.mycompose.android.presentation.navigation.NavSavedScreen
import com.mycompose.android.presentation.product.ProductViewModel
import com.mycompose.android.ui.theme.PrimaryColor
import com.mycompose.app.R


@Composable
fun DrawerView(
    modifier: Modifier,
    onDestinationClicked: (route: String) -> Unit
) {


    Box(modifier = modifier.verticalScroll(rememberScrollState())) {

        //
        Column(modifier.fillMaxSize()) {
            ///
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                colorResource(id = R.color.background),
                                Color(0xFF755CD4),
                                Color(0xFF4C48C1)
                            )
                        )
                    ), verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.padding(4.dp)) {

                    Text(
                        text = "",
                        style = MaterialTheme.typography.h3,
                        color = colorResource(id = R.color.white)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "",
                        style = MaterialTheme.typography.h3,
                        color = colorResource(id = R.color.white)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.ic_faq_new),
                    contentDescription = "", contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
            }

            ////
            screensFromDrawer.forEach { screen ->
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { onDestinationClicked(screen.route) })
                        .height(40.dp)
                        .background(color = Color.Transparent)
                ) {

                    Image(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.title,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(35.dp)
                            .width(35.dp)
                            .padding(start = 10.dp)
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(
                        text = screen.title, fontSize = 16.sp, color = PrimaryColor,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
            ////
            ///
        }
        //
    }
}

@Composable
fun DrawerNavHost(navController: NavController, productViewModel: ProductViewModel) {

    NavHost(
        navController = navController as NavHostController,
        startDestination = NavScreens.DrawerScreens.Home.route
    ) {

        composable(NavScreens.DrawerScreens.Home.route) { DrawerHomeScreen(productViewModel = productViewModel) }
        composable(NavScreens.HomeScreens.Favorite.route) { NavFavoriteScreen(productViewModel = productViewModel) }
        composable(NavScreens.HomeScreens.NearBy.route) { NavNearbyScreen(productViewModel = productViewModel) }
        composable(NavScreens.HomeScreens.Reserved.route) { NavReservedScreen(productViewModel = productViewModel) }
        composable(NavScreens.HomeScreens.Saved.route) { NavSavedScreen(productViewModel = productViewModel) }
        composable(NavScreens.DrawerScreens.MyProfile.route) { DrawerMyProfileScreen(productViewModel = productViewModel) }
        composable(NavScreens.DrawerScreens.Settings.route) { DrawerSettingsScreen(productViewModel = productViewModel) }
        composable(NavScreens.DrawerScreens.Help.route) { DrawerHelpScreen(productViewModel = productViewModel) }
        composable(NavScreens.DrawerScreens.AboutUs.route) { DrawerAboutUsScreen(productViewModel = productViewModel) }
        composable(NavScreens.DrawerScreens.Logout.route) { DrawerLogoutScreen(productViewModel = productViewModel) }
    }

}



@Composable
fun DrawerNavBottomBar(
    modifier: Modifier = Modifier,
    screens: List<NavScreens.HomeScreens>,
    navController: NavController
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = "") },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo = navController.graph.startDestinationId
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}