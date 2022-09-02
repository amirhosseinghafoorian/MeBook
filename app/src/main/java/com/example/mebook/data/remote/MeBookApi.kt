package com.example.mebook.data.remote

import com.example.mebook.model.remote.BaseResponse
import com.example.mebook.model.remote.GetFeaturedArticlesResponse
import com.example.mebook.model.remote.GetSingleArticleResponse
import com.example.mebook.model.remote.GetUserArticlesResponse
import com.example.mebook.model.remote.GetUserFeedArticlesResponse
import com.example.mebook.model.remote.GetUserProfileResponse
import com.example.mebook.model.remote.PublishArticleRequest
import com.example.mebook.model.remote.SearchUserResponse
import com.example.mebook.model.remote.ServerTestModel
import com.example.mebook.model.remote.SignInResponse
import com.example.mebook.model.remote.isFollowingResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    @GET("isFollowing")
    suspend fun isFollowing(
        @Query("followerUser") followerUser: String,
        @Query("followingUser") followingUser: String,
    ): BaseResponse<isFollowingResponse>

    @GET("followUser")
    suspend fun followUser(
        @Query("followerUser") followerUser: String,
        @Query("followingUser") followingUser: String,
    ): BaseResponse<Unit>

    @GET("unFollowUser")
    suspend fun unFollowUser(
        @Query("followerUser") followerUser: String,
        @Query("followingUser") followingUser: String,
    ): BaseResponse<Unit>

    @GET("changePassword")
    suspend fun changePassword(
        @Query("username") username: String,
        @Query("password") password: String,
    ): BaseResponse<Unit>

    @GET("userProfile")
    suspend fun userProfile(
        @Query("username") username: String,
    ): BaseResponse<GetUserProfileResponse>

    @GET("getUserArticles")
    suspend fun getUserArticles(
        @Query("username") username: String,
    ): BaseResponse<GetUserArticlesResponse>

    @GET("getSingleArticle")
    suspend fun getSingleArticle(
        @Query("articleId") articleId: Int,
    ): BaseResponse<GetSingleArticleResponse>

    @GET("deleteArticle")
    suspend fun deleteArticle(
        @Query("articleId") articleId: Int,
    ): BaseResponse<Unit>

    @POST("publishArticle")
    suspend fun publishArticle(
        @Body request: PublishArticleRequest
    ): BaseResponse<Unit>

}