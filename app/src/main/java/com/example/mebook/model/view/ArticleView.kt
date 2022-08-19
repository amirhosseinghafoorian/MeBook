package com.example.mebook.model.view

data class ArticleView(
    val articleId: Int,
    val authorUsername: String,
    val title: String,
    val body: String,
    val timestamp: Long,
)