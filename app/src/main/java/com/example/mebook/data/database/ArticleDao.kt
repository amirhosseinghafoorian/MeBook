package com.example.mebook.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mebook.model.database.ArticleEntity
import com.example.mebook.model.database.FeedArticle
import com.example.mebook.model.database.FeedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(vararg article: ArticleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFeed(vararg feed: FeedEntity)

    @Query("SELECT * FROM feed")
    fun getFeed(): Flow<List<FeedArticle>>

    @Query("DELETE FROM articles where articleId = :id")
    fun deleteArticles(id: Int)

    @Query("DELETE FROM feed")
    fun clearFeed()

}