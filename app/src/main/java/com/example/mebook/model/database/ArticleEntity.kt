package com.example.mebook.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val articleId: Int,
    val authorUsername: String,
    val title: String,
    val body: String,
    val publishDate: Long,
)
