package com.mycompose.android.koin.data


data class MoviesRemoteModel(
    var page: Int? = null,
    var results: List<MovieResultRemoteModel>? = null,
    var totalPages: Int? = null,
    var totalResults: Int? = null,
)

data class MovieResultRemoteModel(
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var genreIds: List<Int>? = null,
    var id: Int? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null,
)