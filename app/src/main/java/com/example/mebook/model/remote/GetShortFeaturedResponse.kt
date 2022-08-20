package com.example.mebook.model.remote

data class GetShortFeaturedResponse(
    val shortFeatured: List<GetArticleResponse>
)