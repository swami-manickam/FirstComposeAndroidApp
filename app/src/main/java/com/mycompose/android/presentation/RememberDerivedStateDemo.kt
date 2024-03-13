package com.mycompose.android.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.mycompose.android.data.response.ProductPayload
import com.mycompose.android.presentation.navigation.ShowProductList
import com.mycompose.android.presentation.product.ProductViewModel
import kotlinx.coroutines.launch


@Composable
fun CustomRememberDerivedStateDemo(productViewModel : ProductViewModel) {

    var productPayload: List<ProductPayload>? = mutableListOf()
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


    if (userdata.value != null)
        productPayload = userdata.value

    val state = rememberLazyListState()
    var isEnabled by remember { mutableStateOf(true) }


    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            CustomScrollToTopButton(state, isEnabled)
        }
    ) { padding ->
        LazyColumn(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(productPayload!!) {
               /* Text(text = "Item $it", modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        isEnabled = false
                    })*/
                ShowProductList(it)
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> LoadProductAndPullRefresh(
    products: List<T>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    lazyListState: LazyListState = rememberLazyListState()
) {

    val pullToRefreshState = rememberPullToRefreshState()

    Box(modifier = Modifier
        .nestedScroll(pullToRefreshState.nestedScrollConnection)
        .padding(bottom = 60.dp)) {

        LazyColumn(state = lazyListState) {
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
fun CustomScrollToTopButton(state: LazyListState, isEnabled: Boolean) {


    val scope = rememberCoroutineScope()
    val showScrollToTopBtn by remember(isEnabled) {
        derivedStateOf { state.firstVisibleItemIndex >= 3 && isEnabled }
    }

    if (showScrollToTopBtn) {
        FloatingActionButton(
            modifier = Modifier.padding(bottom = 30.dp),
            onClick = {
            scope.launch {
                state.animateScrollToItem(0)
            }
        }) {
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
        }
    }


}