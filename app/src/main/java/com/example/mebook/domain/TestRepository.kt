package com.example.mebook.domain

import com.example.mebook.model.database.User
import com.example.mebook.model.remote.ServerTestModel
import kotlinx.coroutines.flow.Flow

interface TestRepository {
    suspend fun addUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun getAllUsers(): Flow<List<User>>
    suspend fun getTestData() : ServerTestModel
    suspend fun fetchUser()
}