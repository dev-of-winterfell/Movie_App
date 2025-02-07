package com.example.flickstream.data.repository

import com.example.flickstream.data.api.WatchmodeApi
import com.example.flickstream.data.mapper.WatchMapper
import com.example.flickstream.data.model.TvShow
import com.example.flickstream.data.model.WatchContent
import com.example.flickstream.data.model.response.BaseResponse
import com.example.flickstream.data.model.response.WatchContentResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WatchRepositoryImpl @Inject constructor(
    private val api: WatchmodeApi,
    private val mapper: WatchMapper,
) : WatchRepository {

    override fun getMovies(): Single<List<WatchContent>> {
        return api.getMovies()
            .map { response -> mapper.mapResponseListToWatchContent(response.results) }
    }

    override fun getTvShows(): Single<List<WatchContent>> {
        return api.getTvShows()
            .map { response -> mapper.mapResponseListToWatchContent(response.results) }
    }

    override suspend fun getContentDetails(contentId: String, isMovie: Boolean): WatchContent {
        return if (isMovie) {
            mapper.mapResponseToWatchContent(api.getMovieDetails(contentId.toInt()))
        } else {
            mapper.mapResponseToWatchContent(api.getTvShowDetails(contentId))
        }
    }

    override suspend fun getTVShowDetails(id: String): WatchContentResponse {
        return api.getTvShowDetails(id)
    }

    override suspend fun getMovieDetails(movieId: Int): WatchContent {
        return mapper.mapResponseToWatchContent(api.getMovieDetails(movieId))
    }

    override suspend fun getTrendingMovies(): List<WatchContent> {
        return mapper.mapResponseListToWatchContent(api.getTrendingMovies().results)
    }

    override fun getTrendingTVShows(): Single<BaseResponse<TvShow>> {
        return api.getTrendingTVShows()
    }

    override suspend fun searchContent(query: String): List<WatchContent> {
        return mapper.mapResponseListToWatchContent(api.searchContent(query).results)
    }
}