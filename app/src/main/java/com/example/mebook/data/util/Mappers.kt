package com.example.mebook.data.util

import com.example.mebook.model.database.ArticleEntity
import com.example.mebook.model.database.FeaturedArticle
import com.example.mebook.model.database.FeedArticle
import com.example.mebook.model.remote.GetArticleResponse
import com.example.mebook.model.remote.GetUserResponse
import com.example.mebook.model.view.ArticleItemView
import com.example.mebook.model.view.UserItemView

fun ArticleEntity.toArticleItemView(): ArticleItemView {
    return ArticleItemView(
        articleId = articleId,
        authorUsername = authorUsername,
        title = title,
        publishDate = publishDate,
    )
}

fun GetUserResponse.toUserItemView(): UserItemView {
    return UserItemView(
        id = id,
        joinDate = joinDate,
        username = username
    )
}

fun List<FeedArticle>.feedToArticleView(): List<ArticleItemView> {
    return this.map {
        it.articleEntity.toArticleItemView()
    }
}

fun List<FeaturedArticle>.featuredToArticleView(): List<ArticleItemView> {
    return this.map {
        it.articleEntity.toArticleItemView()
    }
}

fun GetArticleResponse.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        articleId = articleId,
        authorUsername = authorUsername,
        title = title,
        publishDate = publishDate,
        body = body
    )
}

fun List<GetArticleResponse>.toArticleEntity(): List<ArticleEntity> {
    return this.map {
        it.toArticleEntity()
    }
}