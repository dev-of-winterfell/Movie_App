package com.example.flickstream.presentation.home

import com.example.flickstream.data.model.WatchContent

sealed class HomeState {
    object Loading : HomeState()
    data class Success(
        val movies: List<WatchContent>,
        val tvShows: List<WatchContent>
    ) : HomeState()
    data class Error(val message: String) : HomeState()
}
enum class ContentType {
    MOVIES, TV_SHOWS
}