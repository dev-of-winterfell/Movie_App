package com.example.flickstream.data.model.response

data class WatchContentResponse(
    val id: String,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val release_date: String?,
    val first_air_date: String?,
    val vote_average: Double?,
    val genre_ids: List<Int>?,
    val media_type: String?,
    val popularity: Double?,
    val original_language: String?,
    val name: String? = null  // For TV shows, as they use 'name' instead of 'title'
)

