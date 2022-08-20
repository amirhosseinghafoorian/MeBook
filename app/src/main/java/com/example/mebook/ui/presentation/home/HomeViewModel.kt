package com.example.mebook.ui.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.mebook.domain.LocalRepository
import com.example.mebook.domain.RemoteRepository
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
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
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

    private fun addArticles() {
        makeSuspendCall(
            block = {
                remoteRepository.updateFeed()
            },
            onLoading = { value ->
                updateState { copy(isLoading = value) }
            }
        )
    }

}