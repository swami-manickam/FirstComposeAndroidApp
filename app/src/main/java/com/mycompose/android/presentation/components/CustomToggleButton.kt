package com.mycompose.android.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mycompose.android.ui.theme.spacing
import com.mycompose.app.R


@Composable
fun CustomToggleButton(
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
    isDark: Boolean = isSystemInDarkTheme(),
) {
    val horizontalBias by animateFloatAsState(
        targetValue = when (isDark) {
            true -> 1f
            else -> -1f
        },
        animationSpec = tween(500), label = ""
    )
    val alignment = remember {
        derivedStateOf {
            BiasAlignment(horizontalBias = horizontalBias, verticalBias = 0f)
        }
    }
    Box(
        modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(MaterialTheme.spacing.small)
            )
            .width(64.dp)
            .height(32.dp)
            .background(color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(MaterialTheme.spacing.small))
            .padding(2.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onToggle
            )

    ) {
        Card(
            modifier = Modifier
                .width(28.dp)
                .height(28.dp)
                .padding(2.dp)
                .align(alignment.value),
            shape = RoundedCornerShape(MaterialTheme.spacing.small),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            elevation = CardDefaults.elevatedCardElevation(0.dp),
        ) {}
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize().padding(horizontal = 2.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_star/*"star.xml"*/),
                contentDescription = "", tint = if (isDark) MaterialTheme.colorScheme.background else Color.White,
                modifier = Modifier.size(24.dp).padding(2.dp)
            )
            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = "", tint = if (isDark) Color.White else MaterialTheme.colorScheme.background,
                modifier = Modifier.size(24.dp).padding(2.dp)
            )
        }
    }
}