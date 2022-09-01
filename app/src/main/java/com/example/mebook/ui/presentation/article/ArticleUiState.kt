package com.example.mebook.ui.presentation.article

import com.example.mebook.model.view.FullArticleView

data class ArticleUiState(
    val isOwnArticle: Boolean? = null,
    val isLoadingArticle: Boolean = false,
    val isDeleted: Boolean = false,
    val article: FullArticleView? = null,
)