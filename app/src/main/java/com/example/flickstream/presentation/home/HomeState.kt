package com.example.flickstream.presentation.home

import com.example.flickstream.data.model.WatchContent

sealed class HomeState {
    data object Loading : HomeState()
    data class Success(val contents: List<WatchContent>) : HomeState()
    data class Error(val message: String) : HomeState()
}

enum class ContentType {
    MOVIES, TV_SHOWS
}