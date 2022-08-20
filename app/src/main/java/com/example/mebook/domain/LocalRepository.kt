package com.example.mebook.domain

import com.example.mebook.model.database.ArticleEntity
import com.example.mebook.model.database.FeedEntity
import com.example.mebook.model.view.ArticleItemView
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun addArticles(articles: List<ArticleEntity>)
    suspend fun addFeed(feed: List<FeedEntity>)
    suspend fun getFeed(): Flow<List<ArticleItemView>>
    suspend fun deleteArticles(articleIds: List<Int>)
}