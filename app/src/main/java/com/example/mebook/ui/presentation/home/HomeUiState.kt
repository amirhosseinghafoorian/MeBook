package com.example.mebook.ui.presentation.home

import com.example.mebook.model.view.ArticleView

data class HomeUiState(
    val feed: List<ArticleView> = listOf()
)