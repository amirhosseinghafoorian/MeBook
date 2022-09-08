package com.example.mebook.domain

import com.example.mebook.model.remote.SignInResponse
import com.example.mebook.model.view.ArticleItemView
import com.example.mebook.model.view.FullArticleView
import com.example.mebook.model.view.UserItemView

interface RemoteRepository {
    suspend fun loginUser(username: String, password: String): SignInResponse
    suspend fun signUpUser(username: String, password: String): SignInResponse

    suspend fun updateFeed()
    suspend fun updateFeatured()
    suspend fun getFeed(limit: Int): Pair<List<ArticleItemView>, Boolean>
    suspend fun getFeatured(limit: Int): Pair<List<ArticleItemView>, Boolean>
    suspend fun getUserArticles(username: String): List<ArticleItemView>
    suspend fun getArticle(articleId: Int): FullArticleView
    suspend fun deleteArticle(articleId: Int)
    suspend fun publishArticle(title: String, body: String)

    suspend fun searchUsers(text: String): List<UserItemView>

    suspend fun getUserProfile(username: String): Pair<Int, Int>
    suspend fun isFollowing(username: String): Boolean
    suspend fun followUser(username: String)
    suspend fun unFollowUser(username: String)

    suspend fun changePassword(newPassword: String)
}