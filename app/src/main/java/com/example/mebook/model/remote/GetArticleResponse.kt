package com.example.mebook.model.remote

data class GetArticleResponse(
    val articleId: Int,
    val authorUsername: String,
    val body: String,
    val publishDate: Long,
    val title: String
)