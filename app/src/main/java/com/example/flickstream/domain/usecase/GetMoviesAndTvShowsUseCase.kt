package com.example.flickstream.domain.usecase

import com.example.flickstream.data.model.WatchContent
import com.example.flickstream.data.repository.WatchRepository
import io.reactivex.rxjava3.core.Single

class GetMoviesAndTvShowsUseCase(
    private val repository: WatchRepository
) {
    operator fun invoke(): Single<Pair<List<WatchContent>, List<WatchContent>>> {
        return repository.getMoviesAndTvShows()
    }
}