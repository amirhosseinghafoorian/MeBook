package com.example.mebook.domain

import com.example.mebook.model.remote.SignInResponse
import com.example.mebook.model.view.UserItemView

interface RemoteRepository {
    suspend fun loginUser(username: String, password: String): SignInResponse
    suspend fun signUpUser(username: String, password: String): SignInResponse
    suspend fun updateFeed()
    suspend fun updateFeatured()
    suspend fun searchUsers(text: String): List<UserItemView>
}