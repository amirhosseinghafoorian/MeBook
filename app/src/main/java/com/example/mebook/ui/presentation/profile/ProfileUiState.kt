package com.example.mebook.ui.presentation.profile

data class ProfileUiState(
    val isOwnProfile: Boolean? = null,
    val isFollowingUser: Boolean? = null,
    val username: String? = null,
    val logout: Boolean = false
)