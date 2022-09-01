package com.example.mebook.ui.presentation.search

sealed interface SearchAction {
    data class UpdateTextField(val value: String) : SearchAction
    data class NavigateToUserProfile(val username: String) : SearchAction
}