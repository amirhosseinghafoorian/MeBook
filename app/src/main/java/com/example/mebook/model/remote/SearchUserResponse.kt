package com.example.mebook.model.remote

data class SearchUserResponse(
    val foundUsers: List<GetUserResponse>
)