package com.example.mebook.ui.presentation.home

sealed interface HomeAction {
    object FeedShowMore : HomeAction
    object FeaturedShowMore : HomeAction
    data class FeedItemClick(val id: Int) : HomeAction
    data class FeaturedItemClick(val id: Int) : HomeAction
}