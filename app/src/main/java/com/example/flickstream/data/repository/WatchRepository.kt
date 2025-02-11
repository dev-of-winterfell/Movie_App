package com.example.flickstream.data.repository

import com.example.flickstream.data.model.TvShow
import com.example.flickstream.data.model.WatchContent
import com.example.flickstream.data.model.response.BaseResponse
import com.example.flickstream.data.model.response.WatchContentResponse
import io.reactivex.rxjava3.core.Single

interface WatchRepository {

    fun getMoviesAndTvShows(): Single<Pair<List<WatchContent>, List<WatchContent>>>

    suspend fun getContentDetails(contentId: String, isMovie: Boolean): WatchContent

}