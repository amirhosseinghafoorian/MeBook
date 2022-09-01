package com.example.mebook.model.remote

data class GetUserProfileResponse(
    val articlesCount: Int,
    val followersCount: Int
)