package com.example.mebook.data.util

import com.example.mebook.model.database.ArticleEntity
import com.example.mebook.model.database.FeaturedArticle
import com.example.mebook.model.database.FeaturedEntity
import com.example.mebook.model.database.FeedArticle
import com.example.mebook.model.database.FeedEntity
import com.example.mebook.model.remote.GetArticleResponse
import com.example.mebook.model.view.ArticleItemView

fun ArticleEntity.toArticleView(): ArticleItemView {
    return ArticleItemView(
        articleId = articleId,
        authorUsername = authorUsername,
        title = title,
        timestamp = timestamp,
    )
}

fun List<FeedArticle>.feedToArticleView(): List<ArticleItemView> {
    return this.map {
        it.articleEntity.toArticleView()
    }
}

fun List<FeaturedArticle>.featuredToArticleView(): List<ArticleItemView> {
    return this.map {
        it.articleEntity.toArticleView()
    }
}

fun GetArticleResponse.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        articleId = articleId,
        authorUsername = authorUsername,
        title = title,
        timestamp = timeStamp,
        body = body
    )
}

fun List<GetArticleResponse>.toArticleEntity(): List<ArticleEntity> {
    return this.map {
        it.toArticleEntity()
    }
}

fun Int.toFeedEntity(): FeedEntity {
    return FeedEntity(
        articleId = this
    )
}

fun Int.toFeaturedEntity(): FeaturedEntity {
    return FeaturedEntity(
        articleId = this
    )
}

fun List<Int>.toFeedEntity(): List<FeedEntity> {
    return this.map {
        it.toFeedEntity()
    }
}