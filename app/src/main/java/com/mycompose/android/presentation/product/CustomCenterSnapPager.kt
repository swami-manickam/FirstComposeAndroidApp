package com.mycompose.android.presentation.product

import androidx.compose.animation.core.Spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.mycompose.android.data.response.ProductPayload
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.mycompose.app.R


@Suppress("PreviewAnnotationInFunctionWithParameters")
@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun CustomCenterSnapPager(productPayload: List<ProductPayload>?) {



    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.size(10.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Horizontal Center Snap",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.background)
            )
            TextButton(onClick = {}) {
                Text(
                    text = stringResource(id = R.string.see_all),
                    color = colorResource(id = R.color.background)
                )
            }
        }
    }

    if (productPayload.isNullOrEmpty()) {
        return
    }

    //val pagerState = rememberPagerState(pageCount = { productPayload?.size!!})
    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) {
        productPayload?.size ?: 0
    }

    val coroutineScope = rememberCoroutineScope()
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray))
        val pageSize = 200.dp
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(pageSize = pageSize),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp),
            contentPadding = PaddingValues(
                start = (maxWidth - pageSize) / 2,
                end = (maxWidth - pageSize) / 2
            ),
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState,
                pagerSnapDistance = PagerSnapDistance.atMost(30),
                snapAnimationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
            )
        ) { page ->


            CircleFilterItem(payload = productPayload[page],
                pagerState = pagerState,
                page = page,
                onPageSelected = { payload ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(page)
                    }
                }
            )


        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CircleFilterItem(
    payload: ProductPayload?,
    pagerState: PagerState,
    page: Int,
    onPageSelected: (ProductPayload) -> Unit,
) {
    Column(modifier = Modifier
        .clickable {
            onPageSelected(payload!!)
        }
        .graphicsLayer {
            // Calculate the absolute offset for the current page from the
            // scroll position. We use the absolute value which allows us to mirror
            // any effects for both directions
            val pageOffset = ((pagerState.currentPage - page) + pagerState
                .currentPageOffsetFraction).absoluteValue


            // We animate the scaleX + scaleY, between 85% and 100%
            lerp(
                start = 0.85f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            ).also { scale ->
                scaleX = scale
                scaleY = scale
            }

            // We animate the alpha, between 50% and 100%
            alpha = lerp(
                start = 0.25f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        }
    ) {
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            if (pagerState.currentPage == page) {
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }
            /*Image(
                painter = painterResource(id = filter.imagePreview),
                contentDescription = filter.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
            )*/

                AsyncImage(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(payload?.strDrinkThumb)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = null,
                    /*colorFilter = ColorFilter.colorMatrix(matrix)*/
                )
            }
        payload?.let {
            Text(
                it.strCategory,
                maxLines = 1,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
