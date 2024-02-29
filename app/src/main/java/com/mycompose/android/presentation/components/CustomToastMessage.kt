package com.mycompose.android.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter


@Composable
fun CustomToastMessage(
    modifier: Modifier = Modifier,
    state: Boolean = false,
    message: String = "",
    isError: Boolean = false,
    successIcon: Painter,
    warningIcon: Painter = successIcon,
) {
    AnimatedVisibility(
        visible = state,
        enter = slideInVertically { it },
        exit = slideOutVertically { it },
        modifier = modifier
    ) {
        CustomSnackBar(
            iconTint = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
            iconBackgroundColor = if (isError) MaterialTheme.colorScheme.errorContainer  else MaterialTheme.colorScheme.primary,
            icon = if (isError) warningIcon else successIcon
        ) {
            Text(
                text = message,
                color = if (isError)MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}