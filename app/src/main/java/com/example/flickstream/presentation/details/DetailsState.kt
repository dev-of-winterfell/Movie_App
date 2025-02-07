package com.example.flickstream.presentation.details

import com.example.flickstream.data.model.WatchContent

sealed class DetailsState {
    data object Loading : DetailsState()
    data class Success(val content: WatchContent) : DetailsState()
    data class Error(val message: String) : DetailsState()
}