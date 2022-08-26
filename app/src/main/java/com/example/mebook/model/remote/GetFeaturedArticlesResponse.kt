package com.example.mebook.model.remote

data class GetFeaturedArticlesResponse(
    val featuredArticles: List<GetArticleResponse>
)