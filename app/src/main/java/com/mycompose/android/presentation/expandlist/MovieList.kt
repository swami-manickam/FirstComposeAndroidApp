@file:OptIn(ExperimentalComposeUiApi::class)

package com.mycompose.android.presentation.expandlist

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mycompose.android.data.model.MovieModel
import com.mycompose.app.R

@Composable
fun MovieListItem(movieModel: MovieModel, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    )
    {
        var expanded by remember { mutableStateOf(false) }
        Column(
            modifier = modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {

            /*ConstraintLayout(modifier = modifier.padding(horizontal = 10.dp, vertical = 10.dp)) {
                val (title, textDate,
                    textEpisode, image) = createRefs()

                MovieImage(heroIcon = movieModel.imageResourceId)
                MovieInformation(movieModel.name, movieModel.realName)
                Box(contentAlignment = Alignment.CenterEnd) {
                    MovieItemButton(expanded = expanded,
                        onClick = { expanded = !expanded })
                }
            }*/



            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .sizeIn(minHeight = 72.dp)
            ) {
                MovieImage(heroIcon = movieModel.imageResourceId)
                MovieInformation(movieModel.name, movieModel.realName)
                Spacer(Modifier.weight(1f))
                Box(contentAlignment = Alignment.CenterEnd) {
                    MovieItemButton(expanded = expanded,
                        onClick = { expanded = !expanded })
                }
            }

            if (expanded) {
                MovieMoreInfo(movieModel.superpowers)
            }

        }
    }
}

@Composable
fun MovieImage(@DrawableRes heroIcon: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(72.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(heroIcon),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun MovieInformation(heroName: Int, heroRealName: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(start = 16.dp, top = 8.dp)
    ) {
        Text(
            text = stringResource(heroName),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = stringResource(heroRealName),
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun MovieTopAppBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(56.dp),
        contentAlignment = Alignment.Center
    )
    {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1
        )

    }
}

@Composable
private fun MovieItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

        IconButton(onClick = onClick) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                tint = MaterialTheme.colors.secondary,
                contentDescription = stringResource(R.string.expand_button_content_description)
            )
        }

}

@Composable
fun MovieMoreInfo(
    @StringRes heroMoreInfo: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            start = 16.dp,
            top = 8.dp,
            bottom = 16.dp,
            end = 16.dp
        )
    )
    {
        Text(
            text = stringResource(R.string.superpowers),
            style = MaterialTheme.typography.h5
        )

        Text(
            text = stringResource(heroMoreInfo),
            style = MaterialTheme.typography.body1
        )
    }

}