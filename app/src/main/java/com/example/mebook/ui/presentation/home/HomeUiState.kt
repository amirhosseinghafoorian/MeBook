package com.example.mebook.ui.presentation.home

import com.example.mebook.model.view.ArticleItemView

data class HomeUiState(
    val isLoading: Boolean = false,
    val canShowMoreFeed: Boolean = false,
    val canShowMoreFeatured: Boolean = false,
    val feed: List<ArticleItemView> = listOf(),
    val featured: List<ArticleItemView> = listOf()
)