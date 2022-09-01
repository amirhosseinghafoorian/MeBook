package com.example.mebook.ui.presentation.full_articles

interface FullArticlesAction {
    data class OnItemClick(val id: Int) : FullArticlesAction
    object ShowMore : FullArticlesAction
    object NavigateUp : FullArticlesAction
}