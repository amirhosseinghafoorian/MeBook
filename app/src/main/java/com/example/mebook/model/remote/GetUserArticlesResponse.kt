package com.example.mebook.model.remote

data class GetUserArticlesResponse(
    val userArticles: List<GetArticleResponse>
)