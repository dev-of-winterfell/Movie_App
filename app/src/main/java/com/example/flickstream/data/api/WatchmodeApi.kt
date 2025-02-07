package com.example.flickstream.data.api

import com.example.flickstream.data.model.TvShow
import com.example.flickstream.data.model.response.BaseResponse
import com.example.flickstream.data.model.response.WatchContentListResponse
import com.example.flickstream.data.model.response.WatchContentResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WatchmodeApi {
    // RxJava endpoints
    @GET("movie/popular")
    fun getMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Single<WatchContentListResponse>

    @GET("tv/popular")
    fun getTvShows(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Single<WatchContentListResponse>

    @GET("movie/{content_id}")
    fun getContentDetails(
        @Path("content_id") contentId: String,
        @Query("language") language: String = "en-US"
    ): Single<WatchContentResponse>

    // Coroutine endpoints
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("language") language: String = "en-US"
    ): WatchContentListResponse

    @GET("trending/tv/day")
    suspend fun getTrendingTvShows(
        @Query("language") language: String = "en-US"
    ): WatchContentListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US"
    ): WatchContentResponse

    @GET("trending/tv/day")
    fun getTrendingTVShows(): Single<BaseResponse<TvShow>>

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetails(
        @Path("tv_id") tvId: String,
        @Query("language") language: String = "en-US"
    ): WatchContentResponse
    @GET("search/multi")
    suspend fun searchContent(
        @Query("query") query: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): WatchContentListResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}