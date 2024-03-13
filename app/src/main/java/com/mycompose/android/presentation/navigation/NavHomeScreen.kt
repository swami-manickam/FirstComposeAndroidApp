@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.mycompose.android.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mycompose.android.data.response.ProductPayload
import com.mycompose.android.presentation.CustomRememberDerivedStateDemo
import com.mycompose.android.presentation.CustomTabLayout
import com.mycompose.android.presentation.expandlist.MovieExpand
import com.mycompose.android.presentation.navigation.view.NavScreens
import com.mycompose.android.presentation.product.HorizontalPagerWithIndicators
import com.mycompose.android.presentation.product.ProductViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun DrawerHomeScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Home)
    Column(
        modifier = modifier.fillMaxSize()/*,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally*/
    ) {
        /*Text(text = "Nav Home.", style = MaterialTheme.typography.h4)*/
        MovieExpand()
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
        // Text(text = "MyProfile Screen", style = MaterialTheme.typography.h4)


        val userdata = productViewModel.userList.observeAsState(null)

        LaunchedEffect(Unit) {
            productViewModel.getProductDetails()
        }

        if (userdata.value == null)
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }


        var isRefreshing by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()

        if (userdata.value != null) {
            /*LoadProductDetail(productPayload = userdata.value)*/
            LoadProductAndPullRefresh(
                products = userdata.value!!,
                isRefreshing = isRefreshing,
                onRefresh = {
                    scope.launch {
                        isRefreshing = true
                        delay(3000L)
                        isRefreshing = false
                    }
                })
        }

    }
}

@Composable
fun LoadProductDetail(productPayload: List<ProductPayload>?) =
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
        items(productPayload!!) { payload ->
            ShowProductList(payload)
        }
    }


@Composable
fun <T> LoadProductAndPullRefresh(
    products: List<T>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    lazyListState: LazyGridState = rememberLazyGridState()
) {

    val pullToRefreshState = rememberPullToRefreshState()

    Box(modifier = Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection).padding(bottom = 60.dp)) {

        LazyVerticalGrid(state = lazyListState, columns = GridCells.Adaptive(minSize = 140.dp)) {
            items(products) {
                ShowProductList(product = it as ProductPayload)
            }
        }

        if (pullToRefreshState.isRefreshing) {
            LaunchedEffect(true) {
                onRefresh()
            }
        }

        LaunchedEffect(isRefreshing) {
            if (isRefreshing)
                pullToRefreshState.startRefresh()
            else
                pullToRefreshState.endRefresh()


        }
        if (isRefreshing)
            PullToRefreshContainer(
                state = pullToRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )

    }
}


@Composable
fun ShowProductList(product: ProductPayload) {
    Surface(
        shape = androidx.compose.material3.MaterialTheme.shapes.medium,
        shadowElevation = 1.dp,
        modifier = Modifier
            .padding(8.dp)
            .nestedScroll(rememberNestedScrollInteropConnection())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    androidx.compose.material3.MaterialTheme.colorScheme.onPrimary,
                    RectangleShape
                )
                .padding(8.dp)
        ) {
            Column {
                AsyncImage(
                    model = product.strDrinkThumb,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            border = ButtonDefaults.outlinedButtonBorder,
                            shape = RoundedCornerShape(3.dp)
                        )
                )
                Spacer(modifier = Modifier.width(4.dp))
                androidx.compose.material3.Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = product.strCategory,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(1.dp))
                androidx.compose.material3.Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = product.strAlcoholic,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = androidx.compose.material3.MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.width(3.dp))
                androidx.compose.material3.Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = product.strDrink,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                )
                /*Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .background(Color.Cyan),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(
                        text = "How many cars are in the garage",
                        textAlign = TextAlign.Center
                    )
                }*/
            }
        }
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
fun DrawerHelpScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
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
fun DrawerLogoutScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {

}


@Composable
fun NavFavoriteScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Home)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*Text(text = "Favorite Screen", style = MaterialTheme.typography.h4)*/
        HorizontalPagerWithIndicators(productViewModel)
    }
}


@Composable
fun NavNearbyScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Settings)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*Text(text = "Nearby Screen", style = MaterialTheme.typography.h4)*/
        CustomTabLayout(productViewModel)
    }
}


@Composable
fun NavReservedScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Help)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*Text(text = "Reserved Screen", style = MaterialTheme.typography.h4)*/
        CustomRememberDerivedStateDemo(productViewModel)
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