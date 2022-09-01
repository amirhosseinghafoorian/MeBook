package com.example.mebook.ui.presentation.profile

data class ProfileUiState(
    val isChangePasswordSheetOpen: Boolean = false,
    val isFollowingUser: Boolean? = null,
    val isOwnProfile: Boolean? = null,
    val isLoadingChangePassword: Boolean = false,
    val isLoadingUserProfile: Boolean = false,
    val userProfile: Pair<Int, Int>? = null,
    val username: String? = null,
    val logout: Boolean = false,
)