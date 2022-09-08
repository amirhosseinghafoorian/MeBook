package com.example.mebook.data.repository

import com.example.mebook.data.remote.MeBookApi
import com.example.mebook.data.util.getOrThrow
import com.example.mebook.data.util.toArticleEntity
import com.example.mebook.data.util.toArticleItemView
import com.example.mebook.data.util.toFullArticleView
import com.example.mebook.data.util.toUserItemView
import com.example.mebook.domain.DataStoreRepository
import com.example.mebook.domain.LocalRepository
import com.example.mebook.domain.RemoteRepository
import com.example.mebook.model.database.FeaturedEntity
import com.example.mebook.model.database.FeedEntity
import com.example.mebook.model.remote.PublishArticleRequest
import com.example.mebook.model.remote.SignInResponse
import com.example.mebook.model.view.ArticleItemView
import com.example.mebook.model.view.FullArticleView
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

    override suspend fun getFeed(limit: Int): Pair<List<ArticleItemView>, Boolean> {
        val result = api
            .getUserFeedArticles(dataStoreRepository.getUsername(), limit)
            .getOrThrow()

        return Pair(
            result.feedArticles
                .map { response ->
                    response.toArticleItemView()
                },
            result.canShowMore
        )
    }

    override suspend fun getFeatured(limit: Int): Pair<List<ArticleItemView>, Boolean> {
        val result = api
            .getFeaturedArticles(dataStoreRepository.getUsername(), limit)
            .getOrThrow()

        return Pair(
            result
                .featuredArticles
                .map { response ->
                    response.toArticleItemView()
                },
            result.canShowMore
        )
    }

    override suspend fun getUserArticles(username: String): List<ArticleItemView> {
        return api
            .getUserArticles(username)
            .getOrThrow()
            .userArticles
            .map { response ->
                response.toArticleItemView()
            }
    }

    override suspend fun getArticle(articleId: Int): FullArticleView {
        return api
            .getSingleArticle(articleId)
            .getOrThrow()
            .article
            .toFullArticleView()
    }

    override suspend fun deleteArticle(articleId: Int) {
        api.deleteArticle(articleId).getOrThrow()
    }

    override suspend fun publishArticle(title: String, body: String) {
        api.publishArticle(
            PublishArticleRequest(
                articleBody = body,
                articleTitle = title,
                author = dataStoreRepository.getUsername()
            )
        )
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

    override suspend fun getUserProfile(username: String): Pair<Int, Int> {
        val result = api.userProfile(username).getOrThrow()
        return Pair(result.articlesCount, result.followersCount)
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

    override suspend fun changePassword(newPassword: String) {
        api.changePassword(
            username = dataStoreRepository.getUsername(),
            password = newPassword
        ).getOrThrow()
    }
}