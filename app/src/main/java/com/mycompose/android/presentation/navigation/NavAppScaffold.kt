package com.mycompose.android.presentation.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberScaffoldState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.mycompose.android.presentation.navigation.view.DrawerNavBottomBar
import com.mycompose.android.presentation.navigation.view.DrawerNavHost
import com.mycompose.android.presentation.navigation.view.DrawerView
import com.mycompose.android.presentation.navigation.view.NavScreens
import com.mycompose.android.presentation.navigation.view.screensInHomeFromBottomNav
import com.mycompose.android.presentation.product.ProductViewModel
import com.mycompose.android.utils.BackPressHandler
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NavAppScaffold() {

    val viewModel: ProductViewModel = viewModel()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val currentScreen by viewModel.currentScreen.observeAsState()


   /* if (scaffoldState.drawerState.isOpen) {
        BackPressHandler {
            scope.launch {
                scaffoldState.drawerState.close()
            }
        }
    }
*/

    var topBar: @Composable () -> Unit = {
        TopBar(title = currentScreen!!.title,
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            })
    }

    val bottomBar: @Composable () -> Unit = {
        //if (currentScreen == NavScreens.DrawerScreens.Home || currentScreen is NavScreens.HomeScreens) {
            DrawerNavBottomBar(
                navController = navController,
                screens = screensInHomeFromBottomNav
            )
        //}
    }


    Scaffold(
        topBar = topBar,
        bottomBar = { bottomBar()},
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerView(Modifier.padding(0.dp)) { route ->
                scope.launch {
                    scaffoldState.drawerState.close()
                }
                navController.navigate(route) {
                    popUpTo = navController.graph.startDestinationId
                    launchSingleTop = true
                }

            }
        }
    ) {
        DrawerNavHost(navController = navController, productViewModel = viewModel)

    }


}




@Composable
fun TopBar(title: String = "", buttonIcon: ImageVector, onButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() }) {
                Icon(buttonIcon, contentDescription = "")
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}