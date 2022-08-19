package com.example.mebook.data.util

import com.example.mebook.model.database.FeedArticle
import com.example.mebook.model.view.ArticleView

fun FeedArticle.toArticleView(): ArticleView {
    return ArticleView(
        articleId = articleEntity.articleId,
        authorUsername = articleEntity.authorUsername,
        title = articleEntity.title,
        body = articleEntity.body,
        timestamp = articleEntity.timestamp,
    )
}

fun List<FeedArticle>.toArticleView(): List<ArticleView> {
    return this.map {
        it.toArticleView()
    }
}