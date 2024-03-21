package com.mycompose.android.presentation.product


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.mycompose.android.data.response.ProductPayload
import com.mycompose.android.presentation.hotels.HotelHomeScreen
import com.mycompose.app.R
import kotlinx.coroutines.launch
import kotlin.math.abs

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
     Column(
         modifier = Modifier
             .fillMaxWidth()
             .height(250.dp)
             .padding(16.dp)
     ) {
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


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        BuildHorizontalSlider(productPayload)

        Spacer(modifier = Modifier.height(20.dp))


        HotelHomeScreen()




    }

}


@Composable
fun DisplayHorizontalPagerContent(pageNo: Int, payload: ProductPayload) {

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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BuildHorizontalSlider(productPayload: List<ProductPayload>?) {


    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) {
        productPayload?.size ?: 0
    }

    // Vertical pager state
    val verticalPagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) {
        productPayload?.size ?: 0
    }



   /* LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { currentPage ->
                verticalPagerState.animateScrollToPage(currentPage)
            }
    }*/

    Column( modifier = Modifier.fillMaxWidth().height(400.dp)) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) { page ->
                val offset = pagerState.currentPage + pagerState.currentPageOffsetFraction - page

                /*val matrix = remember { ColorMatrix() }

                val pageOffset = (pagerState.currentPage -page) + pagerState.currentPageOffsetFraction
                val imageSize by animateFloatAsState(targetValue = if (pageOffset != 0.0f) 0.75f else 1f,
                    animationSpec = tween(durationMillis = 300), label = ""
                )


                LaunchedEffect(key1 = imageSize){
                    if(pageOffset != 0.0f)
                        matrix.setToSaturation(0f)
                    else
                        matrix.setToSaturation(1f)
                }*/

                Card(
                    colors = CardDefaults.cardColors(Color.Transparent),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(0.dp),
                    modifier = Modifier.fillMaxWidth()
                        .graphicsLayer {
                            val scaleFactor = lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = abs(offset)
                            )
                            scaleX = scaleFactor
                            scaleY = scaleFactor
                            alpha = lerp(
                                start = 0.6f,
                                stop = 1f,
                                fraction = 1f - abs(offset)
                            )

                            /*scaleX = imageSize
                            scaleY = imageSize*/
                        }
                        .zIndex(1f - abs(offset))
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(productPayload?.get(page)?.strDrinkThumb)
                            .crossfade(true)
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = null,
                        /*colorFilter = ColorFilter.colorMatrix(matrix)*/
                    )
                }
            }

        }


        /*VerticalPager(
            state = verticalPagerState) { page ->

            Column(
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            ) {
                Text(
                    text = productPayload?.get(page)?.strCategory!!,
                    style = MaterialTheme.typography.h6,
                    color = Color.Black

                )
                Text(
                    text = productPayload[page].strAlcoholic,
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }

        }*/

    }
}


