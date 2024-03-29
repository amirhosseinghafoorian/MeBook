package com.example.mebook.ui.presentation.profile

sealed interface ProfileAction {
    object Logout : ProfileAction
    object NavigateUp : ProfileAction
    data class Navigate(val route: String) : ProfileAction
    data class ToggleFollowState(val isFollowing: Boolean) : ProfileAction
    data class ChangePassword(val newPassword: String) : ProfileAction
}