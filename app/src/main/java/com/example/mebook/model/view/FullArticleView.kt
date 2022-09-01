package com.example.mebook.model.view

data class FullArticleView(
    val articleId: Int,
    val authorUsername: String,
    val body: String,
    val publishDate: Long,
    val title: String
)
