package com.mycompose.android.ui.theme.screen

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycompose.android.app.AppConstants
import com.mycompose.android.presentation.product.ProductListActivity
import com.mycompose.app.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(onTimeout: () -> Unit /*currentContext : Context*/) {

    val currentOnTimeout by rememberUpdatedState(onTimeout)


    val fontSize = 38.sp
    val currentFontSizePx = with(LocalDensity.current) {
        fontSize.toPx()
    }
    val currentFontSizeDoublePx = currentFontSizePx * 2

    val infiniteTransition = rememberInfiniteTransition(label = "text animation")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = currentFontSizeDoublePx,
        animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing)),
        label = "text animate"
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.inversePrimary
        ),
        start = Offset(offset, offset),
        end = Offset(offset + currentFontSizePx, offset + currentFontSizePx),
        tileMode = TileMode.Mirror
    )

    var currentRotation by remember { mutableFloatStateOf(0f) }
    val rotation = remember { Animatable(currentRotation) }

    LaunchedEffect(key1 = Unit) {
        rotation.animateTo(
            targetValue = currentRotation + 360f,
            animationSpec = infiniteRepeatable(animation = tween(3000, easing = LinearEasing))
        ) {
            currentRotation = value
        }
        delay(AppConstants.SPLASH_SCREEN_TIME)
        currentOnTimeout()

        /*currentContext.startActivity(Intent(currentContext, ProductListActivity::class.java))*/
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(id = R.drawable.ic_faq_new),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .rotate(rotation.value),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.app_name), style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    brush = brush
                ),
                fontSize = fontSize,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun LandingScreen() {
    var visible by remember { mutableStateOf(false) }

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .background(
                        colorResource(id = R.color.purple_700)
                    )
                    .fillMaxSize(),
            ) {
                AnimatedVisibility(
                    visible = visible,
                    enter = slideInVertically(
                        initialOffsetY = {
                            // Slide in from top
                            -it
                        },
                        animationSpec = tween(
                            durationMillis = 3000,
                            easing = LinearEasing
                        )
                    ),
                ) {
                    Column(
                        verticalArrangement =
                        Arrangement.Center,
                        horizontalAlignment =
                        Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(
                                0.dp,
                                0.dp,
                                0.dp,
                                0.dp
                            )
                            .background(
                                colorResource(
                                    id = R.color.purple_700
                                )
                            )
                            .fillMaxSize()
                    ) {
                        Text(
                            stringResource(
                                id = R.string.app_name
                            ),
                            fontSize = 36.sp,
                            modifier = Modifier.padding(
                                bottom =
                                dimensionResource(
                                    com.intuit.sdp.R.dimen._20sdp
                                )
                            ),
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .height(
                                    dimensionResource(
                                        com.intuit.sdp.R.dimen._60sdp
                                    )
                                )
                                .width(dimensionResource(
                                    com.intuit.sdp.R.dimen._60sdp
                                )
                                )
                                .clip(
                                    RoundedCornerShape(
                                        8.dp
                                    )
                                )
                                .background(color = Color.White)
                        )
                    }
                }
            }
            LaunchedEffect(true) {
                visible = true
            }
        }
    )
}