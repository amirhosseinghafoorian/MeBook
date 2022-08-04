package com.example.mebook.data.repository

import com.example.mebook.data.database.TestDao
import com.example.mebook.data.remote.MyTestApi
import com.example.mebook.domain.TestRepository
import com.example.mebook.model.database.User
import com.example.mebook.model.remote.ServerTestModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val api: MyTestApi,
    private val dao: TestDao,
) : TestRepository {

    override suspend fun addUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        dao.removeUser(user)
    }

    override suspend fun getAllUsers(): Flow<List<User>> = dao.getAllUsers()

    override suspend fun getTestData(): ServerTestModel = api.getMyApiData()

    override suspend fun fetchUser() {
        val nameFromApi = api.getMyApiData().name
        dao.insertUser(User(1, nameFromApi))
    }

}