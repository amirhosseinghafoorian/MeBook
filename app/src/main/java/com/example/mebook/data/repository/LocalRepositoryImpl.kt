package com.example.mebook.data.repository

import com.example.mebook.data.database.ArticleDao
import com.example.mebook.data.util.toArticleView
import com.example.mebook.domain.LocalRepository
import com.example.mebook.model.database.ArticleEntity
import com.example.mebook.model.database.FeedEntity
import com.example.mebook.model.view.ArticleItemView
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao,
) : LocalRepository {

    override suspend fun addArticles(articles: List<ArticleEntity>) {
        delay(1000)
        articles.forEach {
            articleDao.insertArticle(it)
        }
    }

    override suspend fun addFeed(feed: List<FeedEntity>) {
        delay(1000)
        feed.forEach {
            articleDao.insertFeed(it)
        }
    }

    override suspend fun clearFeed() {
        articleDao.clearFeed()
    }

    override suspend fun getFeed(): Flow<List<ArticleItemView>> {
        return articleDao.getFeed().map {
            it.toArticleView()
        }
    }

    override suspend fun deleteArticles(articleIds: List<Int>) {
        articleIds.forEach {
            articleDao.deleteArticles(it)
        }
    }

}