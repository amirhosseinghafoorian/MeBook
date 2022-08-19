package com.example.mebook.ui.presentation.home

import com.example.mebook.model.view.ArticleView

data class HomeUiState(
    val isLoading : Boolean = false,
    val feed: List<ArticleView> = listOf(),
    val featured: List<ArticleView> = listOf()
)