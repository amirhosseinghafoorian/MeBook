package com.example.mebook.model.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "featured",
    foreignKeys = [ForeignKey(
        entity = ArticleEntity::class,
        parentColumns = arrayOf("articleId"),
        childColumns = arrayOf("articleId"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class FeaturedEntity(
    @PrimaryKey val articleId: Int,
    val publishDate: Long,
)

