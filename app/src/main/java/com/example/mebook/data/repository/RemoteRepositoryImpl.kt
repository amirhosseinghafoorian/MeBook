package com.example.mebook.data.repository

import com.example.mebook.data.remote.MeBookApi
import com.example.mebook.data.util.getOrThrow
import com.example.mebook.data.util.toArticleEntity
import com.example.mebook.data.util.toArticleItemView
import com.example.mebook.data.util.toUserItemView
import com.example.mebook.domain.DataStoreRepository
import com.example.mebook.domain.LocalRepository
import com.example.mebook.domain.RemoteRepository
import com.example.mebook.model.database.FeaturedEntity
import com.example.mebook.model.database.FeedEntity
import com.example.mebook.model.remote.SignInResponse
import com.example.mebook.model.view.ArticleItemView
import com.example.mebook.model.view.UserItemView
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
        val articleList = api
            .getUserFeedArticles(dataStoreRepository.getUsername())
            .getOrThrow()
            .feedArticles
        val feedList = articleList.map { article ->
            FeedEntity(article.articleId, article.publishDate)
        }

        localRepository.clearFeed()

        localRepository.addArticles(
            articleList.toArticleEntity()
        )
        localRepository.addFeed(feedList)
    }

    override suspend fun updateFeatured() {
        val articleList = api
            .getFeaturedArticles(dataStoreRepository.getUsername())
            .getOrThrow()
            .featuredArticles
        val featuredList = articleList.map { article ->
            FeaturedEntity(article.articleId, article.publishDate)
        }

        localRepository.addArticles(
            articleList.toArticleEntity()
        )
        localRepository.clearFeatured()
        localRepository.addFeatured(featuredList)
    }

    override suspend fun getFeed(limit: Int): List<ArticleItemView> {
        return api
            .getUserFeedArticles(dataStoreRepository.getUsername(), limit)
            .getOrThrow()
            .feedArticles
            .map { response ->
                response.toArticleItemView()
            }
    }

    override suspend fun getFeatured(limit: Int): List<ArticleItemView> {
        return api
            .getFeaturedArticles(dataStoreRepository.getUsername(), limit)
            .getOrThrow()
            .featuredArticles
            .map { response ->
                response.toArticleItemView()
            }
    }

    override suspend fun searchUsers(text: String): List<UserItemView> {
        return api
            .searchUser(dataStoreRepository.getUsername(), text)
            .getOrThrow()
            .foundUsers
            .map {
                it.toUserItemView()
            }
    }

    override suspend fun isFollowing(username: String): Boolean {
        return api.isFollowing(
            followerUser = dataStoreRepository.getUsername(),
            followingUser = username
        ).getOrThrow().isFollowing
    }

    override suspend fun followUser(username: String) {
        api.followUser(
            followerUser = dataStoreRepository.getUsername(),
            followingUser = username
        ).getOrThrow()
    }

    override suspend fun unFollowUser(username: String) {
        api.unFollowUser(
            followerUser = dataStoreRepository.getUsername(),
            followingUser = username
        ).getOrThrow()
    }
}