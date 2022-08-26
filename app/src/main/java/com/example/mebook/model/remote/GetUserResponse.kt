package com.example.mebook.model.remote

data class GetUserResponse(
    val id: Int,
    val joinDate: Long,
    val username: String
)