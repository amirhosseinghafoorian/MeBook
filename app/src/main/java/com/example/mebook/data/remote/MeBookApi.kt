package com.example.mebook.data.remote

import com.example.mebook.model.remote.BaseResponse
import com.example.mebook.model.remote.GetFeaturedArticlesResponse
import com.example.mebook.model.remote.GetUserFeedArticlesResponse
import com.example.mebook.model.remote.SearchUserResponse
import com.example.mebook.model.remote.ServerTestModel
import com.example.mebook.model.remote.SignInResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MeBookApi {

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

    @GET("getUserFeedArticles")
    suspend fun getUserFeedArticles(
        @Query("username") username: String,
        @Query("limit") limit: Int? = null,
    ): BaseResponse<GetUserFeedArticlesResponse>

    @GET("getFeaturedArticles")
    suspend fun getFeaturedArticles(
        @Query("username") username: String,
        @Query("limit") limit: Int? = null,
    ): BaseResponse<GetFeaturedArticlesResponse>

    @GET("searchUser")
    suspend fun searchUser(
        @Query("username") username: String,
        @Query("searchUsername") searchUsername: String,
    ): BaseResponse<SearchUserResponse>

}