@file:OptIn(ExperimentalPagerApi::class)

package com.mycompose.android.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsBaseball
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.SportsCricket
import androidx.compose.material.icons.filled.SportsGolf
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.mycompose.android.presentation.navigation.NavNearbyScreen
import com.mycompose.android.presentation.navigation.view.NavScreens
import com.mycompose.android.presentation.product.HorizontalPagerWithIndicators
import com.mycompose.android.presentation.product.ProductViewModel
import kotlinx.coroutines.launch


data class CustomTabData(val tabName: String, val tabIcon: ImageVector)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTabLayout(viewModel: ProductViewModel) {

    val tabData = listOf(
        CustomTabData("BasketBall", Icons.Filled.SportsBasketball),
        CustomTabData("Golf", Icons.Filled.SportsGolf),
        CustomTabData("BaseBall", Icons.Filled.SportsBaseball),
        CustomTabData("Cricket", Icons.Filled.SportsCricket),
        CustomTabData("Soccer", Icons.Filled.SportsSoccer),
        CustomTabData("Tennis", Icons.Filled.SportsTennis)
    )

    val pagerState = rememberPagerState(initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { tabData.size })

    val scope = rememberCoroutineScope()

    var tabIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxWidth()) {
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            divider = {
                Spacer(modifier = Modifier.height(5.dp))
            },
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    /*modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),*/
                    modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    height = 5.dp,
                    color = Color.White
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(),

            ) {
            tabData.forEachIndexed { index, s ->
                /*LeadingIcon*/Tab(selected = /*tabIndex*/pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            tabIndex = index
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    icon = {
                        Icon(
                            /*painter = painterResource(id = R.drawable.ic_faq_new),*/
                            imageVector = tabData[index].tabIcon,
                            contentDescription = null
                        )
                    },
                    text = {
                        Text(text = tabData[index].tabName)
                    }
                )
            }
        }

        CustomTabScreens(viewModel = viewModel, pagerState)

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTabScreens(viewModel: ProductViewModel, pagerState: PagerState) {

    HorizontalPager(state = pagerState) { index ->

        when (index) {
            0 -> /*BasketBallTabScreen(productViewModel = viewModel)*/HorizontalPagerWithIndicators(viewModel)
            1 -> BaseballTabScreen(productViewModel = viewModel)
            2 -> GolfTabScreen(productViewModel = viewModel)
            3 -> CricketTabScreen(productViewModel = viewModel)
            4 -> SoccerTabScreen(productViewModel = viewModel)
            5 -> TennisTabScreen(productViewModel = viewModel)
        }
    }

}



@Composable
fun BasketBallTabScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Settings)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "BasketBall Screen", style = MaterialTheme.typography.h4)
    }
}

@Composable
fun BaseballTabScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Settings)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Baseball Screen", style = MaterialTheme.typography.h4)
    }
}

@Composable
fun GolfTabScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Settings)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Golf Screen", style = MaterialTheme.typography.h4)
    }
}


@Composable
fun CricketTabScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Settings)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cricket Screen", style = MaterialTheme.typography.h4)
    }
}

@Composable
fun SoccerTabScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Settings)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Soccer Screen", style = MaterialTheme.typography.h4)
    }
}


@Composable
fun TennisTabScreen(modifier: Modifier = Modifier, productViewModel: ProductViewModel) {
    productViewModel.setCurrentScreen(NavScreens.DrawerScreens.Settings)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Tennis Screen", style = MaterialTheme.typography.h4)
    }
}


