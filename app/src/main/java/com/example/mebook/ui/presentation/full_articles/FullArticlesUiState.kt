package com.example.mebook.ui.presentation.full_articles

import com.example.mebook.model.view.ArticleItemView

data class FullArticlesUiState(
    val type: String,
    val articlesLimit: Int = 10,
    val articles: List<ArticleItemView> = listOf(),
)