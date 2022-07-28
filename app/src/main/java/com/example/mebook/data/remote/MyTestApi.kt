package com.example.mebook.data.remote

import com.example.mebook.model.remote.MyTestModel
import retrofit2.http.GET

interface MyTestApi {

    @GET("entries")
    suspend fun getMyApiData(): MyTestModel

}