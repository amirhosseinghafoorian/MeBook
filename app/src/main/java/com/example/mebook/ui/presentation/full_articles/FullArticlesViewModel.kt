package com.example.mebook.ui.presentation.full_articles

import androidx.lifecycle.SavedStateHandle
import com.example.mebook.AppConstants.FULL_ARTICLE_FEATURED_TYPE
import com.example.mebook.AppConstants.FULL_ARTICLE_FEED_TYPE
import com.example.mebook.domain.RemoteRepository
import com.example.mebook.ui.presentation.full_articles.FullArticlesAction.ShowMore
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FullArticlesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val remoteRepository: RemoteRepository
) : BaseViewModel<FullArticlesAction, FullArticlesUiState>(
    FullArticlesUiState(
        type = savedStateHandle.get<String>("type")!!
    )
) {

    override fun onAction(action: FullArticlesAction) {
        when (action) {
            ShowMore -> updateArticlesList()
            else -> throw IllegalArgumentException("unsupported action")
        }
    }

    init {
        updateArticlesList()
    }

    private fun updateArticlesList() {
        when (uiState.value.type) {
            FULL_ARTICLE_FEATURED_TYPE -> getFeatured()
            FULL_ARTICLE_FEED_TYPE -> getFeed()
        }
    }

    private fun getFeatured() {
        makeSuspendCall(
            block = {
                remoteRepository.getFeatured(uiState.value.articlesLimit)
            }, onSuccess = { list ->
                updateState { copy(articles = list) }
                updateLimit()
            }
        )
    }

    private fun getFeed() {
        makeSuspendCall(
            block = {
                remoteRepository.getFeed(uiState.value.articlesLimit)
            }, onSuccess = { list ->
                updateState { copy(articles = list) }
                updateLimit()
            }
        )
    }

    private fun updateLimit() {
        val currentLimit = uiState.value.articlesLimit
        updateState { copy(articlesLimit = currentLimit + 10) }
    }

}