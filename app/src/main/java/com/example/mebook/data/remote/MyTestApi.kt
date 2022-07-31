package com.example.mebook.data.remote

import com.example.mebook.model.remote.ServerTestModel
import retrofit2.http.GET

interface MyTestApi {

    @GET("testResponse")
    suspend fun getMyApiData(): ServerTestModel

}