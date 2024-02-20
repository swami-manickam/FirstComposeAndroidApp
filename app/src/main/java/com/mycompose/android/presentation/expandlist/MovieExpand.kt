package com.mycompose.android.presentation.expandlist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.unit.dp
import com.mycompose.android.data.repo.MovieRepository.movies


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun  MovieExpand() {
    Surface(modifier = Modifier.nestedScroll(rememberNestedScrollInteropConnection())) {
        Scaffold(
            /*topBar = { MovieTopAppBar()}*/
        ) {
                innerPadding ->

            Column(Modifier.fillMaxSize().padding(bottom = 60.dp)) {
                LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background).wrapContentHeight()) {
                    items(movies){
                            movieList -> MovieListItem(movieModel = movieList)
                    }
                }
            }
        }
    }

}



fun Modifier.paddingBotAndTopBar(): Modifier {
    return padding(top = 0.dp, bottom = 80.dp)
}

fun Modifier.paddingTopBar(): Modifier {
    return padding(top = 64.dp)
}