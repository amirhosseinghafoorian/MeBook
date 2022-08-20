package com.example.mebook.model.database

import androidx.room.Embedded
import androidx.room.Relation

data class FeaturedArticle(
    @Embedded val featuredEntity: FeaturedEntity,
    @Relation(
        parentColumn = "articleId",
        entityColumn = "articleId"
    )
    val articleEntity: ArticleEntity
)
