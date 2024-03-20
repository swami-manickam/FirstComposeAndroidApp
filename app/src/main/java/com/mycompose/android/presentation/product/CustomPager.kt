package com.mycompose.android.presentation.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.mycompose.android.data.response.ProductPayload
import com.mycompose.app.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerWithIndicators(productViewModel: ProductViewModel) {

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

    val pagerState = rememberPagerState(initialPage = 0,
        initialPageOffsetFraction = 0.5f, pageCount = { productPayload?.size!! })
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
        .padding(16.dp)) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 20.dp), pageSpacing = 10.dp
        ) { page ->

            productPayload?.get(page)?.let { DisplayHorizontalPagerContent(page, it) }


            LaunchedEffect(pagerState) {
                snapshotFlow { pagerState.currentPage }
                    .collect { currentPage ->
                        pagerState.animateScrollToPage(currentPage)
                    }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp, bottom = 10.dp)
        ) {
            HorizontalPagerIndicator(
                pageCount = productPayload?.size!!,
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable {
                        val currentPage = pagerState.currentPage
                        val totalPages = productPayload.size
                        val nextPage =
                            if (currentPage < totalPages - 1) currentPage + 1 else 0
                        coroutineScope.launch { pagerState.animateScrollToPage(nextPage) }
                    },
                activeColor = MaterialTheme.colors.primary,
                inactiveColor = MaterialTheme.colors.secondary
            )
        }
    }
}


@Composable
fun DisplayHorizontalPagerContent(pageNo: Int,payload : ProductPayload) {

    /*Card(shape = RoundedCornerShape(10.dp), modifier = Modifier.graphicsLayer {
        val pageOffset = calculateCurrentOffsetForPage(pageNo).ab
    }) {

    }*/
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            contentAlignment = Alignment.Center,
        ) {


            ImageFromURLWithPlaceHolder(payload.strDrinkThumb)
            Text(
                text = payload.strCategory,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
        }

}


@Composable
fun ImageFromURLWithPlaceHolder(imageUrl: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_faq_new),
        contentDescription = stringResource(R.string.app_name),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    )
}