package com.example.mebook.ui.presentation.profile

sealed interface ProfileAction {
    object Logout : ProfileAction
    object NavigateUp : ProfileAction
    data class ToggleFollowState(val isFollowing: Boolean) : ProfileAction
}