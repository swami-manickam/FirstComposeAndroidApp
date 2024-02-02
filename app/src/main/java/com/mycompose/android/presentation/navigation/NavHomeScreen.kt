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
fun NavHomeScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
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
fun NavSettingsScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
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
fun NavHelpScreen(modifier: Modifier = Modifier,productViewModel: ProductViewModel) {
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
fun NavAboutUsScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
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
fun LogoutScreen(modifier: Modifier = Modifier,productViewModel: ProductViewModel) {

}