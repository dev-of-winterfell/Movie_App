package com.example.flickstream.domain.usecase

import com.example.flickstream.data.model.WatchContent
import com.example.flickstream.data.repository.WatchRepository
import javax.inject.Inject

class GetContentDetailsUseCase @Inject constructor(
    private val repository: WatchRepository
) {
    suspend operator fun invoke(contentId: String, isMovie: Boolean): WatchContent {
        return repository.getContentDetails(contentId, isMovie)
    }
}