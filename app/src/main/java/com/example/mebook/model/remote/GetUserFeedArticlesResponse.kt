package com.example.mebook.model.remote

data class GetUserFeedArticlesResponse(
    val feedArticles: List<GetArticleResponse>,
    val canShowMore: Boolean
)