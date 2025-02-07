package com.example.flickstream.domain.usecase


import com.example.flickstream.data.model.WatchContent
import com.example.flickstream.data.repository.WatchRepository

import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: WatchRepository
) {
    operator fun invoke(): Single<List<WatchContent>> {
        return repository.getMovies()
    }
}
