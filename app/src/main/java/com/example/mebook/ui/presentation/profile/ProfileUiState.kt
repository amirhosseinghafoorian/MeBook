package com.example.mebook.ui.presentation.profile

data class ProfileUiState(
    val isChangePasswordSheetOpen: Boolean = false,
    val isFollowingUser: Boolean? = null,
    val isOwnProfile: Boolean? = null,
    val isLoading: Boolean = false,
    val username: String? = null,
    val logout: Boolean = false,
)