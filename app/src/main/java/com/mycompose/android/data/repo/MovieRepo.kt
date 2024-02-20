package com.mycompose.android.data.repo

import com.mycompose.android.data.model.MovieModel
import com.mycompose.app.R

object MovieRepository {
    val movies = listOf(
        MovieModel(R.drawable.deadpool, R.string.deadpool, R.string.deadpool_name, R.string.deadpool_powers),
        MovieModel(R.drawable.deadpool, R.string.namor, R.string.namor_name, R.string.namor_powers),
        MovieModel(R.drawable.deadpool, R.string.ghost, R.string.ghost_name, R.string.ghost_powers),
        MovieModel(R.drawable.deadpool, R.string.moon, R.string.moon_name, R.string.moon_powers),
        MovieModel(R.drawable.deadpool, R.string.hawkeye, R.string.hawkeye_name, R.string.hawkeye_powers),
        MovieModel(R.drawable.deadpool, R.string.blade, R.string.blade_name, R.string.blade_powers),
        MovieModel(R.drawable.deadpool, R.string.strange, R.string.strange_name, R.string.strange_powers),
        MovieModel(R.drawable.deadpool, R.string.vision, R.string.vision_name, R.string.vision_powers),
        MovieModel(R.drawable.deadpool, R.string.widow, R.string.widow_name, R.string.widow_powers),
        MovieModel(R.drawable.deadpool, R.string.daredevil, R.string.daredevil_name, R.string.daredevil_powers),
        MovieModel(R.drawable.deadpool, R.string.panther, R.string.panther_name, R.string.panther_powers),
        MovieModel(R.drawable.deadpool, R.string.hulk, R.string.hulk_name, R.string.hulk_powers)
    )
}