package com.example.mebook.model.remote

data class GetShortFeedResponse(
    val shortFeed: List<GetArticleResponse>
)