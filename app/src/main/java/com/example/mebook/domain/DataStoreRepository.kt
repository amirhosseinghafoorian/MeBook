package com.example.mebook.domain

interface DataStoreRepository {

    suspend fun isLoggedIn(): Boolean
    suspend fun setLogin(username: String)
    suspend fun setLogout()
    suspend fun getUsername(): String

}