package com.example.mebook.model.database

import androidx.room.Embedded
import androidx.room.Relation

data class FeedArticle(
    @Embedded val feedEntity: FeedEntity,
    @Relation(
        parentColumn = "articleId",
        entityColumn = "articleId"
    )
    val articleEntity: ArticleEntity
)
