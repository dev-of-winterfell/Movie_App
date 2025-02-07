package com.example.flickstream.data.mapper

import com.example.flickstream.data.model.TvShow
import com.example.flickstream.data.model.WatchContent
import com.example.flickstream.data.model.response.WatchContentResponse
import javax.inject.Inject

class WatchMapper @Inject constructor() {

    fun mapResponseToWatchContent(input: WatchContentResponse): WatchContent {
        val isTvShow = input.first_air_date != null
        return WatchContent(
            id = input.id,
            title = input.title,
            posterUrl = input.poster_path?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
            description = input.overview,
            releaseDate = input.release_date ?: input.first_air_date ?: "",
            type = if (isTvShow) "tv_show" else "movie",
            isMovie = !isTvShow,

        )
    }

    fun mapResponseListToWatchContent(input: List<WatchContentResponse>): List<WatchContent> {
        return input.map { mapResponseToWatchContent(it) }
    }
    // Add this method inside the class
    fun mapTvShowToWatchContent(tvShow: TvShow): WatchContent {
        return WatchContent(
            id = tvShow.id.toString(),
            title = tvShow.name,
            posterUrl = "https://image.tmdb.org/t/p/w500${tvShow.posterPath}",
            description = tvShow.overview,
            releaseDate = tvShow.firstAirDate,
            type = "tv",
            isMovie = false
        )
    }



}

// Keep extension functions if you still need them
fun WatchContentResponse.toWatchContent(): WatchContent {
    val isTvShow = first_air_date != null
    return WatchContent(
        id = id,
        title = title,
        posterUrl = poster_path?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
        description = overview,
        releaseDate = release_date ?: first_air_date ?: "",
        type = if (isTvShow) "tv_show" else "movie",
        isMovie = !isTvShow
    )
}

fun List<WatchContentResponse>.toWatchContentList(): List<WatchContent> {
    return map { it.toWatchContent() }
}