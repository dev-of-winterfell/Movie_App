package com.example.flickstream.data.model.response


data class WatchContentListResponse(
    val page: Int,
    val results: List<WatchContentResponse>,
    val total_pages: Int,
    val total_results: Int
)