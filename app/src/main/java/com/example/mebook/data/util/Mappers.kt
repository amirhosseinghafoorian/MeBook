package com.example.mebook.data.util

import com.example.mebook.model.database.ArticleEntity
import com.example.mebook.model.database.FeedArticle
import com.example.mebook.model.database.FeedEntity
import com.example.mebook.model.remote.GetArticleResponse
import com.example.mebook.model.view.ArticleItemView

fun FeedArticle.toArticleView(): ArticleItemView {
    return ArticleItemView(
        articleId = articleEntity.articleId,
        authorUsername = articleEntity.authorUsername,
        title = articleEntity.title,
        timestamp = articleEntity.timestamp,
    )
}

fun List<FeedArticle>.toArticleView(): List<ArticleItemView> {
    return this.map {
        it.toArticleView()
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

fun List<Int>.toFeedEntity(): List<FeedEntity> {
    return this.map {
        it.toFeedEntity()
    }
}