package com.example.mebook.ui.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.mebook.domain.LocalRepository
import com.example.mebook.model.database.ArticleEntity
import com.example.mebook.model.database.FeedEntity
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel<HomeAction, HomeUiState>(HomeUiState()) {

    override fun onAction(action: HomeAction) {}

    init {
        addArticles()
        addFeed()
        getFeed()
    }

    private fun getFeed() {
        makeSuspendCall(
            block = {
                localRepository.getFeed()
            },
            onSuccess = { flow ->
                viewModelScope.launch {
                    flow.collect { list ->
                        updateState { copy(feed = list) }
                    }
                }
            }
        )
    }

    private fun addFeed() {
        makeSuspendCall(
            block = {
                localRepository.addFeed(
                    listOf(
                        FeedEntity(2),
                        FeedEntity(3),
                        FeedEntity(4),
                    )
                )
            }
        )
    }

    private fun addArticles() {
        makeSuspendCall(
            block = {
                localRepository.addArticles(
                    listOf(
                        ArticleEntity(1, "ali", "title1", "body1", System.currentTimeMillis()),
                        ArticleEntity(2, "reza", "title2", "body2", System.currentTimeMillis()),
                        ArticleEntity(3, "javad", "title3", "body3", System.currentTimeMillis()),
                        ArticleEntity(4, "hossein", "title4", "body4", System.currentTimeMillis()),
                        ArticleEntity(5, "sina", "title5", "body5", System.currentTimeMillis()),
                        ArticleEntity(6, "milad", "title6", "body6", System.currentTimeMillis())
                    )
                )
            }
        )
    }

}