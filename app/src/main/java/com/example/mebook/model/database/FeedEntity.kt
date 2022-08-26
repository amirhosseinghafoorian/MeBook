package com.example.mebook.model.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "feed",
    foreignKeys = [ForeignKey(
        entity = ArticleEntity::class,
        parentColumns = arrayOf("articleId"),
        childColumns = arrayOf("articleId"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class FeedEntity(
    @PrimaryKey val articleId: Int,
    val publishDate: Long
)
