package com.example.mebook.ui.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.mebook.AppConstants.shortArticleCount
import com.example.mebook.domain.LocalRepository
import com.example.mebook.domain.RemoteRepository
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

    override fun onAction(action: HomeAction) {}

    init {
        updateFeatured()
        getFeed()
        getFeatured()
    }

    private fun getFeed() {
        makeSuspendCall(
            block = {
                localRepository.getFeed()
            },
            onSuccess = { flow ->
                flow.onEach { list ->
                    if (uiState.value.feed != list) {
                        if (list.isNotEmpty() && list.size > shortArticleCount) updateState {
                            copy(feed = list.subList(0, shortArticleCount), canShowMoreFeed = true)
                        }
                        else if (list.isNotEmpty()) updateState {
                            copy(feed = list.subList(0, list.size), canShowMoreFeed = false)
                        } else if (list.isEmpty()) updateState {
                            copy(feed = listOf())
                        }
                    }
                }.launchIn(viewModelScope)
            },
            onError = {
                showSnackbar(it.message.toString())
            }
        )
    }

    private fun getFeatured() {
        makeSuspendCall(
            block = {
                localRepository.getFeatured()
            },
            onSuccess = { flow ->
                flow.onEach { list ->
                    if (list.isNotEmpty() && list.size > shortArticleCount) updateState {
                        copy(
                            featured = list.subList(0, shortArticleCount),
                            canShowMoreFeatured = true
                        )
                    }
                    else if (list.isNotEmpty()) updateState {
                        copy(featured = list.subList(0, list.size))
                    }
                }.launchIn(viewModelScope)
            },
            onError = {
                showSnackbar(it.message.toString())
            }
        )
    }

    private fun updateFeed() {
        makeSuspendCall(
            block = {
                remoteRepository.updateFeed()
            },
            onLoading = { value ->
                updateState { copy(isLoading = value) }
            }
        )
    }

    private fun updateFeatured() {
        makeSuspendCall(
            block = {
                remoteRepository.updateFeatured()
            },
            onSuccess = {
                updateFeed()
            },
            onLoading = { value ->
                updateState { copy(isLoading = value) }
            }
        )
    }

}