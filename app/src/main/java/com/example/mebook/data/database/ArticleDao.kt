package com.example.mebook.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mebook.model.database.ArticleEntity
import com.example.mebook.model.database.FeaturedArticle
import com.example.mebook.model.database.FeaturedEntity
import com.example.mebook.model.database.FeedArticle
import com.example.mebook.model.database.FeedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(vararg article: ArticleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeed(vararg feed: FeedEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeatured(vararg featured: FeaturedEntity)

    @Query("SELECT * FROM feed")
    fun getFeed(): Flow<List<FeedArticle>>

    @Query("SELECT * FROM featured")
    fun getFeatured(): Flow<List<FeaturedArticle>>

    @Query("DELETE FROM articles where articleId = :id")
    fun deleteArticles(id: Int)

    @Query("DELETE FROM feed")
    fun clearFeed()

    @Query("DELETE FROM featured")
    fun clearFeatured()

}