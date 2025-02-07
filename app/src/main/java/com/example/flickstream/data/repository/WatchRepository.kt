package com.example.flickstream.data.repository

import com.example.flickstream.data.model.TvShow
import com.example.flickstream.data.model.WatchContent
import com.example.flickstream.data.model.response.BaseResponse
import com.example.flickstream.data.model.response.WatchContentResponse
import io.reactivex.rxjava3.core.Single

interface WatchRepository {
    // RxJava methods (for legacy compatibility)
    fun getMovies(): Single<List<WatchContent>>
    fun getTvShows(): Single<List<WatchContent>>
    fun getTrendingTVShows(): Single<BaseResponse<TvShow>>

    // Coroutine methods
    suspend fun getContentDetails(contentId: String, isMovie: Boolean): WatchContent
    suspend fun getTVShowDetails(id: String): WatchContentResponse
    suspend fun getMovieDetails(movieId: Int): WatchContent
    suspend fun getTrendingMovies(): List<WatchContent>
    suspend fun searchContent(query: String): List<WatchContent>
}