package com.example.mebook.data.repository

import com.example.mebook.data.remote.MeBookApi
import com.example.mebook.data.util.getOrThrow
import com.example.mebook.data.util.toArticleEntity
import com.example.mebook.data.util.toFeaturedEntity
import com.example.mebook.data.util.toFeedEntity
import com.example.mebook.domain.DataStoreRepository
import com.example.mebook.domain.LocalRepository
import com.example.mebook.domain.RemoteRepository
import com.example.mebook.model.remote.SignInResponse
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val api: MeBookApi,
    private val localRepository: LocalRepository,
    private val dataStoreRepository: DataStoreRepository
) : RemoteRepository {

    override suspend fun loginUser(username: String, password: String): SignInResponse {
        return api.loginUser(username, password).getOrThrow()
    }

    override suspend fun signUpUser(username: String, password: String): SignInResponse {
        return api.signUpUser(username, password).getOrThrow()
    }

    override suspend fun updateFeed() {
        dataStoreRepository.getUsername()?.let { username ->

            val articleList = api
                .getUserFeedArticles(username)
                .getOrThrow()
                .feedArticles
            val feedList = articleList.map { article ->
                article.articleId
            }

            localRepository.addArticles(
                articleList.toArticleEntity()
            )
            localRepository.clearFeed()
            localRepository.addFeed(
                feedList.map { it.toFeedEntity() }
            )

        } ?: run {
            throw Exception("Username not found")
        }
    }

    override suspend fun updateFeatured() {
        dataStoreRepository.getUsername()?.let { username ->

            val articleList = api
                .getFeaturedArticles(username)
                .getOrThrow()
                .featuredArticles
            val featuredList = articleList.map { article ->
                article.articleId
            }

            localRepository.addArticles(
                articleList.toArticleEntity()
            )
            localRepository.clearFeatured()
            localRepository.addFeatured(
                featuredList.map { it.toFeaturedEntity() }
            )

        } ?: run {
            throw Exception("Username not found")
        }
    }
}