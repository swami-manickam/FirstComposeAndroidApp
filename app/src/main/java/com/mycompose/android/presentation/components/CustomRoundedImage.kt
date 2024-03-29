package com.mycompose.android.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mycompose.android.ui.theme.spacing

@Composable
fun CustomRoundedImage(
    painter: Painter,
    editPainter: Painter = painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    boxSize: Dp = 72.dp,
    strokeWidth: Dp = 2.dp,
    strokeColor: Color = MaterialTheme.colorScheme.secondary,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    imageScale: ContentScale = ContentScale.Crop,
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(MaterialTheme.spacing.medium))
            .background(backgroundColor)
            .size(boxSize)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(MaterialTheme.spacing.medium)),
            painter = painter,
            contentDescription = "",
            alignment = Alignment.Center,
            contentScale = imageScale
        )
        if (editPainter != painter) {
            Box(
                modifier = Modifier
                    .fillMaxSize().clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                    .background(
                        Color.Black.copy(alpha = 0.4f),
                        shape = MaterialTheme.shapes.large
                    )
                    .size(boxSize)
                    .clickable { onClick() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = editPainter,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                    alignment = Alignment.Center,
                    contentScale = imageScale
                )
            }
        }
    }
}

@Composable
fun CustomRoundedImage(
    bitmap: ImageBitmap?,
    placeholder: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    boxSize: Dp = 72.dp,
    imageSize: Dp = 32.dp,
    strokeWidth: Dp = 2.dp,
    strokeColor: Color = MaterialTheme.colorScheme.secondary,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    imageScale: ContentScale = ContentScale.Crop,
) {

    if (bitmap == null) {
        Box(
            modifier = modifier
                .clip(shape = RoundedCornerShape(MaterialTheme.spacing.medium))
                .background(backgroundColor)
                .size(boxSize)
                .clickable { onClick() }
                .border(
                    width = strokeWidth,
                    color = strokeColor,
                    shape = RoundedCornerShape(MaterialTheme.spacing.medium)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(imageSize),
                painter = placeholder,
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = imageScale
            )

        }
    } else {
        Box(
            modifier = modifier
                .clip(shape = RoundedCornerShape(MaterialTheme.spacing.medium))
                .background(backgroundColor)
                .size(boxSize)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(MaterialTheme.spacing.medium)),
                bitmap = bitmap,
                contentDescription = "",
                alignment = Alignment.Center,
                contentScale = imageScale
            )
        }
    }
}