package com.example.mebook.data.repository

import com.example.mebook.data.remote.MeBookApi
import com.example.mebook.data.util.getOrThrow
import com.example.mebook.data.util.toArticleEntity
import com.example.mebook.data.util.toFeedEntity
import com.example.mebook.domain.LocalRepository
import com.example.mebook.domain.RemoteRepository
import com.example.mebook.model.remote.SignInResponse
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val api: MeBookApi,
    private val localRepository: LocalRepository
) : RemoteRepository {

    override suspend fun loginUser(username: String, password: String): SignInResponse {
        return api.loginUser(username, password).getOrThrow()
    }

    override suspend fun signUpUser(username: String, password: String): SignInResponse {
        return api.signUpUser(username, password).getOrThrow()
    }

    override suspend fun updateFeed() {
        val articleList = api.getShortFeed().getOrThrow()
        val feedList = articleList.shortFeed.map {
            it.articleId
        }
        localRepository.addArticles(articleList.shortFeed.toArticleEntity())
        localRepository.clearFeed()
        localRepository.addFeed(feedList.toFeedEntity())
    }

    override suspend fun updateFeatured() {
        TODO("Not yet implemented")
    }
}