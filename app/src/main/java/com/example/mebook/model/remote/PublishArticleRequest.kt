package com.example.mebook.model.remote

data class PublishArticleRequest(
    val articleBody: String,
    val articleTitle: String,
    val author: String
)