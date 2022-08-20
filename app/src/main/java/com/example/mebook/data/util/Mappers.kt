package com.example.mebook.data.util

import com.example.mebook.model.database.FeedArticle
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