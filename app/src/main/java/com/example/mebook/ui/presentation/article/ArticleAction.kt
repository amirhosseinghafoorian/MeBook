package com.example.mebook.ui.presentation.article

sealed interface ArticleAction {
    object NavigateUp : ArticleAction
}