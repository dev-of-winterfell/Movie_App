package com.example.flickstream.data.model

data class Movie(
    val id: Int,
    val name: String,
    val overview: String?,
    val poster_path: String?,
    val backdrop_path: String?,
    val first_air_date: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val episode_run_time: List<Int>?,
    val genre_ids: List<Int>?,
    val genres: List<Genre>?,
    val number_of_episodes: Int?,
    val number_of_seasons: Int?,
    val original_language: String?,
    val original_name: String?,
    val popularity: Double?,
    val status: String?,
    val type: String?
)
data class Genre(
    val id: Int,
    val name: String
)