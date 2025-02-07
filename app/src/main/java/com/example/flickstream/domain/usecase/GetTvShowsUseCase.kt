package com.example.flickstream.domain.usecase


import com.example.flickstream.data.model.TvShow
import com.example.flickstream.data.model.WatchContent
import com.example.flickstream.data.repository.WatchRepository
import com.example.flickstream.data.mapper.WatchMapper // assuming you have a mapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetTvShowsUseCase @Inject constructor(
    private val repository: WatchRepository,
    private val mapper: WatchMapper
) {
    operator fun invoke(): Single<List<WatchContent>> {
        return repository.getTrendingTVShows()
            .map { response ->
                response.results.map { tvShow ->
                    mapper.mapTvShowToWatchContent(tvShow)
                }
            }
    }
}