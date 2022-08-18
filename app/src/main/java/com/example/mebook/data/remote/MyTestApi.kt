package com.example.mebook.data.remote

import com.example.mebook.model.remote.BaseResponse
import com.example.mebook.model.remote.ServerTestModel
import com.example.mebook.model.remote.SignInResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MyTestApi {

    @GET("testResponse")
    suspend fun getMyApiData(): ServerTestModel

    @GET("loginUser")
    suspend fun loginUser(
        @Query("username") username: String,
        @Query("password") password: String,
    ): BaseResponse<SignInResponse>

    @GET("signUpUser")
    suspend fun signUpUser(
        @Query("username") username: String,
        @Query("password") password: String,
    ): BaseResponse<SignInResponse>

}