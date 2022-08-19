package com.example.mebook.ui.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.mebook.domain.LocalRepository
import com.example.mebook.model.database.ArticleEntity
import com.example.mebook.model.database.FeedEntity
import com.example.mebook.ui.presentation.home.HomeAction.FeaturedItemClick
import com.example.mebook.ui.presentation.home.HomeAction.FeaturedShowMore
import com.example.mebook.ui.presentation.home.HomeAction.FeedItemClick
import com.example.mebook.ui.presentation.home.HomeAction.FeedShowMore
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel<HomeAction, HomeUiState>(HomeUiState()) {

    override fun onAction(action: HomeAction) {
        when (action) {
            is FeedItemClick -> {}
            is FeaturedItemClick -> {}
            FeedShowMore -> addArticles()
            FeaturedShowMore -> {}
        }
    }

    init {
        addArticles()
        getFeed()
    }

    private fun getFeed() {
        makeSuspendCall(
            block = {
                localRepository.getFeed()
            },
            onSuccess = { flow ->
                flow.onEach { list ->
                    if (list.isNotEmpty()) updateState { copy(feed = list) }
                }.launchIn(viewModelScope)
            }
        )
    }

    private fun addFeed() {
        // todo should call the api and add with it's response
        makeSuspendCall(
            block = {
                localRepository.addFeed(
                    listOf(
                        FeedEntity(2),
                        FeedEntity(3),
                        FeedEntity(4),
                    )
                )
            },
            onLoading = { value ->
                if (!value) updateState { copy(isLoading = value) }
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
            }, onSuccess = {
                addFeed()
            },
            onLoading = { value ->
                if (value) updateState { copy(isLoading = value) }
            }
        )
    }

}