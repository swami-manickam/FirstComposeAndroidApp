package com.mycompose.android.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mycompose.android.presentation.navigation.view.NavScreens
import com.mycompose.android.presentation.product.ProductViewModel


@Composable
fun DrawerHomeScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Home)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Nav Home.", style = MaterialTheme.typography.h4)
    }
}

@Composable
fun DrawerMyProfileScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Home)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "MyProfile Screen", style = MaterialTheme.typography.h4)
    }
}


@Composable
fun DrawerSettingsScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Settings)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "App Settings.", style = MaterialTheme.typography.h4)
    }
}


@Composable
fun DrawerHelpScreen(modifier: Modifier = Modifier,productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Help)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Help.", style = MaterialTheme.typography.h4)
    }
}

@Composable
fun DrawerAboutUsScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.AboutUs)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "About us.", style = MaterialTheme.typography.h4)
    }
}

@Composable
fun DrawerLogoutScreen(modifier: Modifier = Modifier,productViewModel: ProductViewModel) {

}


@Composable
fun NavFavoriteScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Home)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Favorite Screen", style = MaterialTheme.typography.h4)
    }
}


@Composable
fun NavNearbyScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Settings)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Nearby Screen", style = MaterialTheme.typography.h4)
    }
}


@Composable
fun NavReservedScreen(modifier: Modifier = Modifier,productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Help)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Reserved Screen", style = MaterialTheme.typography.h4)
    }
}

@Composable
fun NavSavedScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.AboutUs)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Saved Screen", style = MaterialTheme.typography.h4)
    }
}