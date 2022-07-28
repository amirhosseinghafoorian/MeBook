package com.example.mebook.data.repository

import com.example.mebook.data.remote.MyTestApi
import com.example.mebook.domain.TestRepository
import com.example.mebook.model.MyTestModel
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val api: MyTestApi
) : TestRepository {

    override suspend fun getTestData(): MyTestModel {
        return api.getMyApiData()
    }
}