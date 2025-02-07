package com.example.flickstream.domain.model

data class WatchContentDomain(
    val id: Int,
    val title: String,
    val posterPath: String,
    val overview: String,
    val releaseDate: String,
    val rating: Double,
    val type: String, // "movie" or "tv"
    val genres: List<String> = emptyList()
)