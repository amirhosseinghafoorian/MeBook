package com.example.mebook.data.repository

import com.example.mebook.data.remote.MyTestApi
import com.example.mebook.data.util.getOrThrow
import com.example.mebook.domain.RemoteRepository
import com.example.mebook.model.remote.SignInResponse
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val api: MyTestApi,
) : RemoteRepository {

    override suspend fun loginUser(username: String, password: String): SignInResponse {
        return api.loginUser(username, password).getOrThrow()
    }

    override suspend fun signUpUser(username: String, password: String): SignInResponse {
        return api.signUpUser(username, password).getOrThrow()
    }
}