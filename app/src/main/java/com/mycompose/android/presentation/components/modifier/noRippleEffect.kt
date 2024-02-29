package com.mycompose.android.presentation.components.modifier

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@SuppressLint("ModifierFactoryUnreferencedReceiver")
@Composable
fun Modifier.noRippleEffect(onClick: () -> Unit) = clickable(
    interactionSource = remember { MutableInteractionSource() }, indication = null
) {
    onClick()
}