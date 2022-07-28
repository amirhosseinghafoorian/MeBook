package com.example.mebook.domain

import com.example.mebook.model.MyTestModel

interface TestRepository {
    suspend fun getTestData() : MyTestModel
}