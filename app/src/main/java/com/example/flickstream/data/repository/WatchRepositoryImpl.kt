package com.example.flickstream.data.repository

import com.example.flickstream.data.api.WatchmodeApi
import com.example.flickstream.data.mapper.WatchMapper
import com.example.flickstream.data.model.TvShow
import com.example.flickstream.data.model.WatchContent
import com.example.flickstream.data.model.response.BaseResponse
import com.example.flickstream.data.model.response.WatchContentResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WatchRepositoryImpl(
    private val api: WatchmodeApi,
    private val mapper: WatchMapper,
) : WatchRepository {
    override fun getMoviesAndTvShows(): Single<Pair<List<WatchContent>, List<WatchContent>>> {
        return Single.zip(
            api.getMovies()
                .map { mapper.mapResponseListToWatchContent(it.results) },
            api.getTvShows()
                .map { mapper.mapResponseListToWatchContent(it.results) }
        ) { movies, tvShows ->
            Pair(movies, tvShows)
        }
    }

    override suspend fun getContentDetails(contentId: String, isMovie: Boolean): WatchContent {
        return if (isMovie) {
            mapper.mapResponseToWatchContent(api.getMovieDetails(contentId.toInt()))
        } else {
            mapper.mapResponseToWatchContent(api.getTvShowDetails(contentId))
        }
    }
}