package com.example.mebook.domain

import com.example.mebook.model.remote.SignInResponse

interface RemoteRepository {
    suspend fun loginUser(username: String, password: String) : SignInResponse
    suspend fun signUpUser(username: String, password: String) : SignInResponse
}