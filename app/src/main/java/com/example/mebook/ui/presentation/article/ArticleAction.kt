package com.example.mebook.ui.presentation.article

sealed interface ArticleAction {
    object NavigateUp : ArticleAction
    object DeleteArticle : ArticleAction
    data class NavigateToUserProfile(val username: String) : ArticleAction
}