package com.mycompose.android.presentation.components
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.mycompose.android.ui.theme.spacing

@Composable
fun CustomSnackBar(
    icon: Painter,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    iconTint: Color = MaterialTheme.colors.primary,
    iconBackgroundColor: Color = MaterialTheme.colors.secondary,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically { it },
        exit = slideOutVertically { it },
    ) {
        Column(modifier = modifier.fillMaxWidth().padding(horizontal = androidx.compose.material3.MaterialTheme.spacing.medium)) {
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = androidx.compose.material3.MaterialTheme.spacing.medium)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.secondary,
                        shape = RoundedCornerShape(size = androidx.compose.material3.MaterialTheme.spacing.medium)
                    )
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(size = androidx.compose.material3.MaterialTheme.spacing.medium)
                    )
                    .padding(horizontal = androidx.compose.material3.MaterialTheme.spacing.medium,
                        vertical = androidx.compose.material3.MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(androidx.compose.material3.MaterialTheme.spacing.small)
            ) {
                Icon(
                    modifier = Modifier.background(
                        color = iconBackgroundColor,
                        shape = RoundedCornerShape(androidx.compose.material3.MaterialTheme.spacing.small)
                    ).padding(androidx.compose.material3.MaterialTheme.spacing.small),
                    painter = icon,
                    contentDescription = null,
                    tint = iconTint
                )
                content()
            }
        }
    }
}